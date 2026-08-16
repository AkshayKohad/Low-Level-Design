abstract class DataImporter {
    public final void importData() {
        openSource();
        validateSource();
        parseRecords();
        saveRecords();

        if (shouldBackupSource()) {
            backupSource();
        }
    }

    private void openSource() {
        System.out.println("Opening source");
    }

    private void validateSource() {
        System.out.println("Validating source");
    }

    protected abstract void parseRecords();

    private void saveRecords() {
        System.out.println("Saving records");
    }

    protected boolean shouldBackupSource() {
        return false;
    }

    private void backupSource() {
        System.out.println("Backing up source file");
    }
}

class CsvDataImporter extends DataImporter {
    @Override
    protected void parseRecords() {
        System.out.println("Parsing CSV records");
    }
}

class JsonDataImporter extends DataImporter {
    @Override
    protected void parseRecords() {
        System.out.println("Parsing JSON records");
    }

    @Override
    protected boolean shouldBackupSource() {
        return true;
    }
}

public class DataImportTemplateDemo {
    public static void main(String[] args) {
        DataImporter csvImporter = new CsvDataImporter();
        csvImporter.importData();

        DataImporter jsonImporter = new JsonDataImporter();
        jsonImporter.importData();
    }
}
