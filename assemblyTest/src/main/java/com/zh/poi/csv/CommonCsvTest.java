package com.zh.poi.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class CommonCsvTest {

    public static void main(String[] args) throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/student.csv";

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(file_header)
//                .withSkipHeaderRecord(true)
                ;
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 10 ; i++) {
            printer.printRecord("1\\asdf$$%@#)&(*%(&!@#>>??>\"\"'''", "2", "3", "5");
            printer.printComment("comment");
        }
        writer.flush();

        Reader reader = new FileReader(file_name);
        Iterable<CSVRecord> records = csvFormat.parse(reader);
        for (CSVRecord record : records) {
            System.out.println(record.getRecordNumber());
            System.out.println(record.size());
            System.out.println(record.get(0));
            System.out.println(record.get(1));
            System.out.println(record.get(2));
            System.out.println("==============");
        }
    }
}
