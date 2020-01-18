package com.zh.poi.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;

public class CommonCsvTest {

    public static void main(String[] args) throws IOException {
        defaultCsv();
//        excelCsv();
//        informixCsv();
//        informixUnloadCsv();
//        mysqlCsv();
//        rfc4180Csv();
//        tdfCsv();
    }

    static void defaultCsv() throws IOException {
        final String[] file_header = {"id", "name", "address", "age"};
        final String file_name = "d:/csv/default.csv";

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5000000 ; i++) {
            printer.printRecord("\"阿斯顿发斯蒂芬", "2阿斯蒂芬\r\nas阿斯顿发斯蒂芬df", "3,as阿萨德法师打发斯蒂芬df", "阿斯顿发斯蒂芬");
            printer.printRecord();
        }
        printer.flush();
        printer.close();

//        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
//        List<CSVRecord> recordList = parser.getRecords();
//        System.out.println(recordList.size());
//        for (CSVRecord csvRecord : recordList) {
//            System.out.println(csvRecord.toString());
//        }
//        System.out.println("----------------------------------");
    }

    static void excelCsv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/excel.csv";

        CSVFormat csvFormat = CSVFormat.EXCEL.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord("\"", "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }

    static void informixCsv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/informixUnload.csv";

        CSVFormat csvFormat = CSVFormat.INFORMIX_UNLOAD.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord("\"", "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }

    static void informixUnloadCsv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/informixUnloadCsv.csv";

        CSVFormat csvFormat = CSVFormat.INFORMIX_UNLOAD_CSV.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord("\"", "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }

    static void mysqlCsv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/mysql.csv";

        CSVFormat csvFormat = CSVFormat.MYSQL.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord(i, "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }

    static void rfc4180Csv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/rfc4180.csv";

        CSVFormat csvFormat = CSVFormat.RFC4180.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord("\"", "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }

    static void tdfCsv() throws IOException {
        final String[] file_header = {"id", "name", "address"};
        final String file_name = "d:/csv/tdf.csv";

        CSVFormat csvFormat = CSVFormat.TDF.withHeader(file_header);
        Writer writer = new FileWriter(file_name);
        CSVPrinter printer = new CSVPrinter(writer, csvFormat);
        for (int i = 0 ; i < 5 ; i++) {
            if (i == 2) {
                printer.printRecord("第一列", "第二列");
            } else {
                printer.printRecord("\"", "2\r\nasdf", "3,asdf", "5");
                printer.printRecord();
            }
        }
        writer.flush();

        CSVParser parser = new CSVParser(new FileReader(file_name), csvFormat);
        List<CSVRecord> recordList = parser.getRecords();
        System.out.println(recordList.size());
        for (CSVRecord csvRecord : recordList) {
            System.out.println(csvRecord.toString());
        }
        System.out.println("----------------------------------");
    }
}
