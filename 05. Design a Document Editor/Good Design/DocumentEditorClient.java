import java.util.List;

interface DocumentElement {
    public abstract String render();
}

interface Persistence {
    public abstract void save(String data);
}

class TextElement implements DocumentElement {
    private String text;
    public TextElement(String text) {
        this.text = text;
    }
    @Override
    public String render() {
        return "[Text: " + text + "]";
    }
}

class ImageElement implements DocumentElement {
    private String image;
    public ImageElement(String image) {
        this.image = image;
    }
    @Override
    public String render() {
        return "[Image: " + image + "]";
    }
}

class Document {
    private List<DocumentElement> elements;

    public void addElement(DocumentElement element) {
        elements.add(element);
    }

    public String render() {
        String result = "";
        for (DocumentElement element : elements) {
            result += element.render();
        }
        return result;
    }
}

// FileStorage implementation of Persistence
class FileStorage implements Persistence {
    @Override
    public void save(String data) {
        System.out.println("Document saved to file");
    }
}

// Placeholder DBStorage implementation
class DBStorage implements Persistence {
    @Override
    public void save(String data) {
        // Save to DB
        System.out.println("Document saved to DB");
    }
}

// DocumentEditor class managing client interactions
class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String renderedDocument = "";

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        document.addElement(new ImageElement(imagePath));
    }

    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            renderedDocument = document.render();
        }
        return renderedDocument;
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}


// Client usage example
public class DocumentEditorClient {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document, persistence);

        // Simulate a client using the editor with common text formatting features.
        editor.addText("Hello, world!");
        editor.addText("This is a real-world document editor example.");
        editor.addText("Indented text after a tab space.");
        editor.addImage("picture.jpg");

        // Render and display the final document.
        System.out.println(editor.renderDocument());

        editor.saveDocument();
    }
}