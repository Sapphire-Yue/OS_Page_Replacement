import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class LRU {
    
    private int page_frame;
    private String pageReference;
    Vector<Integer> page_frame_vector = new Vector<>();

    public LRU(String pageReference) {
        this.page_frame = Main.page_frame;
        this.pageReference = pageReference;
    }

    public void run() {
        Main.write("--------------LRU-----------------------\r\n");
        int[] page_reference = Arrays.stream(pageReference.split("")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < page_reference.length; i++) {
            boolean isPageFault = true;
            Main.write(page_reference[i] + "\t");
            if (!page_frame_vector.contains(page_reference[i])) {
                if ( page_frame_vector.size() >= page_frame) {
                    page_frame_vector.remove(page_frame - 1);
                    Main.page_replace++;
                }
                page_frame_vector.add(0, page_reference[i]);
                isPageFault = false;
                Main.page_fault++;
            } 
            else {
                page_frame_vector.remove(page_frame_vector.indexOf(page_reference[i]));
                page_frame_vector.add(0, page_reference[i]);
            }
            if (!isPageFault)
                Main.write(page_frame_vector.stream().map(String::valueOf).collect(Collectors.joining("")) + "\tF\r\n");
            else
                Main.write(page_frame_vector.stream().map(String::valueOf).collect(Collectors.joining("")) + "\r\n");
        }
    }
}
