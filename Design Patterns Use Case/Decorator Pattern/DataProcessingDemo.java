interface DataSource{
    String writeData(String data);
}

class PlainTextDataSource implements DataSource{
    @Override
    public String writeData(String data){
        return data;
    }
}

abstract class DataSourceDecorator implements DataSource{
    protected DataSource dataSource;

    public DataSourceDecorator(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    abstract public String writeData(String data);
}

class CompressionDecorator extends DataSourceDecorator{
    public CompressionDecorator(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public String writeData(String data){
        return "[COMPRESSED]" + dataSource.writeData(data);
    }
}


class EncryptionDecorator extends DataSourceDecorator{
    public EncryptionDecorator(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public String writeData(String data){
        return "[ENCRYPTED]" + dataSource.writeData(data);
    }
}

public class DataProcessingDemo{
    public static void main(String[] args){
        DataSource plainText = new PlainTextDataSource();
        System.out.println(plainText.writeData("hello world"));

        DataSource compressed = new CompressionDecorator(plainText);
        System.out.println(compressed.writeData("hello world"));

        DataSource encryptedAndCompressed = new EncryptionDecorator(
                new CompressionDecorator(new PlainTextDataSource())
        );
        System.out.println(encryptedAndCompressed.writeData("hello world"));
    }
}
