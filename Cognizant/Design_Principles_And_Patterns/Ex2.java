// STEP 1: Project — FactoryMethodPatternExample
//
// WHAT IS FACTORY METHOD PATTERN?
// A design pattern where instead of creating objects directly
// using new, we let a factory class decide which object to create.
//
// Real life example:
//   A document management system needs to create Word, PDF, Excel.
//   Instead of doing new WordDocument() everywhere in the code,
//   we ask the factory: "give me a document" and it creates the
//   right one for us.



// STEP 2: Define interface for document types
// All document types must have an open() method
interface Document {
    void open();
}

// STEP 3: Concrete document classes — implement Document
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document (.docx)");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document (.pdf)");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document (.xlsx)");
    }
}


// STEP 4: Factory Method
// Abstract factory class with createDocument() method
// Each concrete factory creates its own document type
abstract class DocumentFactory {
    // factory method — subclasses decide which document to create
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// STEP 5: Test — create different document types using factory
public class Ex2 {

    public static void main(String[] args) {

        // Create factories
        DocumentFactory wordFactory  = new WordDocumentFactory();
        DocumentFactory pdfFactory   = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Use factory to create documents and open them
        Document word  = wordFactory.createDocument();
        Document pdf   = pdfFactory.createDocument();
        Document excel = excelFactory.createDocument();

        word.open();
        pdf.open();
        excel.open();
    }
}
