package br.com.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.java.model.CsvPessoa;

public class LerLinhasComoObjetosOpenCsv {
	
	private final static String COMMA_DELIMITER = ",";

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\administrator\\Documents\\pessoasComoObjeto.csv"))) {

            List<List<String>> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                result.add(Arrays.asList(values));
            }

            System.out.println(result);
        }

	}

}
