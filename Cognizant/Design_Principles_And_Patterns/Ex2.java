package Design_Principles_And_Patterns;

interface Document {
    void open();
    void close();
    void getInfo();
    String getType();
}

class WordDocument implements Document {

    public String getType() {
        return "Word Document (.docx)";
    }

    public void open() {
        System.out.println("[" + getType() + "] Document opened successfully.");
    }

    public void close() {
        System.out.println("[" + getType() + "] Document closed successfully.");
    }

    public void getInfo() {
        System.out.println("Document Type : " + getType());
        System.out.println("Used For      : Text editing and formatting");
        System.out.println("Application   : Microsoft Word");
    }
}

class PdfDocument implements Document {

    public String getType() {
        return "PDF Document (.pdf)";
    }

    public void open() {
        System.out.println("[" + getType() + "] Document opened successfully.");
    }

    public void close() {
        System.out.println("[" + getType() + "] Document closed successfully.");
    }

    public void getInfo() {
        System.out.println("Document Type : " + getType());
        System.out.println("Used For      : Read-only sharing and printing");
        System.out.println("Application   : Adobe Acrobat / PDF Reader");
    }
}

class ExcelDocument implements Document {

    public String getType() {
        return "Excel Document (.xlsx)";
    }

    public void open() {
        System.out.println("[" + getType() + "] Document opened successfully.");
    }

    public void close() {
        System.out.println("[" + getType() + "] Document closed successfully.");
    }

    public void getInfo() {
        System.out.println("Document Type : " + getType());
        System.out.println("Used For      : Data, tables, and spreadsheets");
        System.out.println("Application   : Microsoft Excel");
    }
}

abstract class DocumentFactory {

    public abstract Document createDocument();

    public void openDocument() {
        Document doc = createDocument();
        doc.open();
    }
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class Ex2 {

    public static void main(String[] args) {


        System.out.println("--- Creating Word Document ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.getInfo();
        wordDoc.open();
        wordDoc.close();

        System.out.println();

        System.out.println("--- Creating PDF Document ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.getInfo();
        pdfDoc.open();
        pdfDoc.close();

        System.out.println();

        System.out.println("--- Creating Excel Document ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.getInfo();
        excelDoc.open();
        excelDoc.close();

        System.out.println();

        System.out.println("--- All Factories Together ---");
        DocumentFactory[] allFactories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : allFactories) {
            Document doc = factory.createDocument();
            System.out.println("Created: " + doc.getType());
        }

    }
}