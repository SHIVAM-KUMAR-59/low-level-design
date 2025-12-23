import java.util.List;

class DocumentEditor {
    List<String> documentElements;
    String renderedDocuments;

    public void addText(String text) {
        documentElements.add(text);
    }

    public void addImage(String image) {
        documentElements.add(image);
    }

    public String renderDocument() {
        if (renderedDocuments.isEmpty()) {
            String result = "";
            for (String element : documentElements) {
                if (element.length() > 4 && (element.substring(element.length() - 4).equals(".png") || element.substring(element.length() - 4).equals(".jpg"))) {
                    result += "[Image: " + element + "]";
                } else {
                    result += "[Text: " + element + "]";
                }
            }
            renderedDocuments = result;
        }
        return renderedDocuments;
    }

    public void saveToFile() {
        System.out.println("Saving to file...");
    }
}

public class DocumentEditorClient {
    public static void main (String args[]) {
        DocumentEditor editor = new DocumentEditor();
        editor.addText("Hello");
        editor.addImage("image.png");
        System.out.println(editor.renderDocument());
        editor.saveToFile();
    }
}
