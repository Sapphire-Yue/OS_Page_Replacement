import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class MFU_FIFO {
    
    private int page_frame;
    private String pageReference;
    Vector<Integer> page_frame_vector = new Vector<>();
    Map<Integer, Integer> page_frequency = new LinkedHashMap<>();

    public MFU_FIFO(String pageReference) {
        this.page_frame = Main.page_frame;
        this.pageReference = pageReference;
    }

    public void run() {
        Main.write("--------------Most Frequently Used Page Replacement -----------------------\r\n");
        int[] page_reference = Arrays.stream(pageReference.split("")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < page_reference.length; i++) {
            boolean isPageFault = true;
            Main.write(page_reference[i] + "\t");
            if (!page_frame_vector.contains(page_reference[i])) {
                if ( page_frame_vector.size() >= page_frame) {
                    int maxFreq = Collections.max(page_frequency.values());
                    List<Integer> maxFreqPages = page_frequency.entrySet().stream().filter(entry -> entry.getValue() == maxFreq).map(Map.Entry::getKey).collect(Collectors.toList());
                    int pageToRemove = maxFreqPages.get(0);
                    page_frame_vector.removeElement(pageToRemove);
                    page_frequency.remove(pageToRemove);
                    Main.page_replace++;
                }
                page_frame_vector.add(0, page_reference[i]);
                isPageFault = false;
                page_frequency.put(page_reference[i], 0);
                Main.page_fault++;
            }
            else
                page_frequency.put(page_reference[i], page_frequency.get(page_reference[i]) + 1);
            if (!isPageFault)
                Main.write(page_frame_vector.stream().map(String::valueOf).collect(Collectors.joining("")) + "\tF\r\n");
            else
                Main.write(page_frame_vector.stream().map(String::valueOf).collect(Collectors.joining("")) + "\r\n");
        }
    }
}
