import java.io.*;
import java.util.Scanner;

public class Main {
    static int method, page_fault = 0, page_replace = 0, page_frame;
    static FileWriter fw;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        String name = inface();
        String filename = name.endsWith(".txt") ? name : name + ".txt";
        String page_reference = read(filename);
        Page_Replacement page_replacement = new Page_Replacement(page_reference);
        fw = new FileWriter("out_" + filename);
        bw = new BufferedWriter(fw);
        switch (method) {
            case 1:
                page_replacement.FIFO();
                break;
            case 2:
                page_replacement.LRU();
                break;
            case 3:
                page_replacement.LFU_FIFO();
                break;
            case 4:
                page_replacement.MFU_FIFO();
                break;
            case 5:
                page_replacement.LFU_LRU();
                break;
            case 6:
                page_replacement.FIFO();
                bw.write("Page Fault = " + page_fault + "  Page Replaces = " + page_replace + "  Page Frames = " + page_frame + "\r\n\r\n");
                page_fault = 0; page_replace = 0;
                page_replacement.LRU();
                bw.write("Page Fault = " + page_fault + "  Page Replaces = " + page_replace + "  Page Frames = " + page_frame + "\r\n\r\n");
                page_fault = 0; page_replace = 0;
                page_replacement.LFU_FIFO();
                bw.write("Page Fault = " + page_fault + "  Page Replaces = " + page_replace + "  Page Frames = " + page_frame + "\r\n\r\n");
                page_fault = 0; page_replace = 0;
                page_replacement.MFU_FIFO();
                bw.write("Page Fault = " + page_fault + "  Page Replaces = " + page_replace + "  Page Frames = " + page_frame + "\r\n\r\n");
                page_fault = 0; page_replace = 0;
                page_replacement.LFU_LRU();
                break;
        }
        bw.write("Page Fault = " + page_fault + "  Page Replaces = " + page_replace + "  Page Frames = " + page_frame + "\r\n");
        bw.flush();
        bw.close();
        fw.close();
    }

    private static String inface() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter File Name (eg. input1 、 input1.txt) : ");
        String name = scanner.nextLine();
        scanner.close();
        return name;
    }
    private static String read(String filename) throws Exception {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] firstLine = br.readLine().split(" ");
        method = Integer.parseInt(firstLine[0]);
        page_frame = Integer.parseInt(firstLine[1]);
        String page_reference = br.readLine();
        br.close();
        return page_reference;
    }
    public static void write(String data) {
        try {
            bw.write(data);
        }
        catch (IOException e) {
            System.err.println("Error to write the file" + e.getMessage());
        }
        
    }
}