package wordFinder;

public class Main {
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        String[] fileName = {"/home/vadim/IdeaProjects/findWord/src/text 3",
                "/home/vadim/IdeaProjects/findWord/src/text 2",
                "/home/vadim/IdeaProjects/findWord/src/text 1"};
        String[] words = {"testing1", "supertest", "testing2", "testing3"};
        String res;
        Finder finder1 = new Finder(fileName, words);
        Finder finder2 = new Finder(fileName, words);
        finder1.start();
        finder2.start();


        /*Finder finder = new Finder(fileName, words);
        Map<String, List> dict = finder.getOccurencies();
        try {
            File file = new File("res.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map.Entry<String, List> entry : dict.entrySet()) {
                fileOutputStream.write((entry.getKey() + ": " + entry.getValue().toString() + "\r\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
