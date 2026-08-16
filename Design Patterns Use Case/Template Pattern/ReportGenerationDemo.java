abstract class ReportGenerator{
    public final void generateReport(){
        fetchData();
        formatData();
        exportReport();
    }
    private void fetchData(){
        System.out.println("Fetching sales data");
    }
    abstract protected void formatData();
    abstract protected void exportReport();
}

class CsvReportGenerator extends ReportGenerator{
    @Override
    protected void formatData(){
        System.out.println("Formatting data as CSV");
    }

    @Override
    protected void exportReport(){
        System.out.println("Exporting CSV report");
    }
}

class PdfReportGenerator extends ReportGenerator{
    @Override
    protected void formatData(){
        System.out.println("Formatting data as PDF");
    }

    @Override
    protected void exportReport(){
        System.out.println("Exporting PDF report");
    }
}

public class ReportGenerationDemo{
    public static void main(String[] args){
        ReportGenerator csvReport = new CsvReportGenerator();
        csvReport.generateReport();

        ReportGenerator pdfReport = new PdfReportGenerator();
        pdfReport.generateReport();
    }
}
