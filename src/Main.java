import java.io.*;

abstract class Baseline {
    protected String title;
    protected String content;
    protected String author;

    public Baseline(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public abstract void display();
    public abstract void publish();
}

interface Readable {
    void read();
    void review();
    void like();
}

class Behaviour extends Baseline implements Readable {
    private String behaviorType;
    protected int analysisResult;

    public Behaviour(String title, String content, String author, String behaviorType, int analysisResult) {
        super(title, content, author);
        this.behaviorType = behaviorType;
        this.analysisResult = analysisResult;
    }

    public String getBehaviorType() {
        return behaviorType;
    }

    @Override
    public void display() {
        System.out.println("Заголовок: " + title);
        System.out.println("Содержание: " + content);
        System.out.println("Тип поведения: " + behaviorType);
    }

    @Override
    public void publish() {
        System.out.println("Behaviour опубликован.");
    }

    @Override
    public void read() {
        System.out.println("Behaviour прочитан.");
    }

    @Override
    public void review() {
        System.out.println("Поведение рецензировано.");
    }

    @Override
    public void like() {
        System.out.println("Поставлен лайк за поведение.");
    }
}

class Blog extends Baseline implements Readable {
    private String blogType;
    public String url;

    public String getBlogType() {
        return blogType;
    }

    public Blog(String title, String content, String author, String blogType, String url) {
        super(title, content, author);
        this.blogType = blogType;
        this.url = url;
    }

    @Override
    public void display() {
        System.out.println("Заголовок: " + title);
        System.out.println("Содержание: " + content);
        System.out.println("Тип блога: " + blogType);
        System.out.println("URL: " + url);
    }

    @Override
    public void publish() {
        System.out.println("Blog опубликован.");
    }

    @Override
    public void read() {
        System.out.println("Blog прочитан.");
    }

    @Override
    public void review() {
        System.out.println("Блог рецензирован.");
    }

    @Override
    public void like() {
        System.out.println("Поставлен лайк за блог.");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream printStream = new PrintStream(System.out);

        int choice;

        do {
            printStream.println("Введите данные Behaviour:");
            printStream.print("Заголовок: ");
            String behaviourTitle = reader.readLine();
            printStream.print("Содержание: ");
            String behaviourContent = reader.readLine();
            printStream.print("Автор: ");
            String behaviourAuthor = reader.readLine();
            printStream.print("Тип поведения: ");
            String behaviorType = reader.readLine();
            printStream.print("Результат анализа: ");
            int analysisResult = Integer.parseInt(reader.readLine());

            Behaviour behaviour = new Behaviour(behaviourTitle, behaviourContent, behaviourAuthor, behaviorType, analysisResult);

            printStream.println("\nВведите данные Blog:");
            printStream.print("Заголовок: ");
            String blogTitle = reader.readLine();
            printStream.print("Содержание: ");
            String blogContent = reader.readLine();
            printStream.print("Автор: ");
            String blogAuthor = reader.readLine();
            printStream.print("Тип поведения: ");
            String blogBehaviorType = reader.readLine();
            printStream.print("Результат анализа: ");
            int blogAnalysisResult = Integer.parseInt(reader.readLine());
            printStream.print("Тип блога: ");
            String blogType = reader.readLine();
            printStream.print("URL: ");
            String blogURL = reader.readLine();

            Blog blog = new Blog(blogTitle, blogContent, blogAuthor, blogBehaviorType, blogURL);

            //Запись в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
                writer.write("Behaviour Data:\n");
                writer.write("Title: " + behaviour.title + "\n");
                writer.write("Content: " + behaviour.content + "\n");
                writer.write("Author: " + behaviour.author + "\n");
                writer.write("Behavior Type: " + behaviour.getBehaviorType() + "\n");
                writer.write("Analysis Result: " + behaviour.analysisResult + "\n");

                writer.write("\nBlog Data:\n");
                writer.write("Title: " + blog.title + "\n");
                writer.write("Content: " + blog.content + "\n");
                writer.write("Author: " + blog.author + "\n");
                writer.write("Blog Type: " + blog.getBlogType() + "\n");
                writer.write("URL: " + blog.url + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Считывание данных из файла
            try (BufferedReader fileReader = new BufferedReader(new FileReader("data.txt"))) {
                String line;
                printStream.println("\nСчитанные данные из файла:");
                while ((line = fileReader.readLine()) != null) {
                    printStream.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            printStream.println("\nЖелаете продолжить?");
            printStream.println("1. Продолжить");
            printStream.println("2. Выйти");
            printStream.print("Введите ваш выбор: ");
            choice = Integer.parseInt(reader.readLine());
        } while (choice == 1);

        printStream.println("Программа завершена.");
    }
}
