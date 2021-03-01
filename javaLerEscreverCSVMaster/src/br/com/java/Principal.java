package br.com.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import br.com.java.model.CsvPessoa;

public class Principal {

	public static void main(String[] args) throws IOException {
	
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escolha 1 - para ler, 2 - escrever, 3 - LerLinhasComoObjetosOpenCsv, 4 - CriarCsvComObjetosOpenCsv");
		
		int num = entrada.nextInt();
		
		 switch (num) {
	     case 1:
	    	 lerCSV();
	       break;
	     case 2:
	    	 escreverCSV();
	       break;
	     case 3:
	    	 LerLinhasComoObjetosOpenCsv();
	       break;
	     case 4:
	    	 CriarCsvComObjetosOpenCsv();
	       break;
	     default:
	       System.out.println("Número inválido");
	}
}

	private static void CriarCsvComObjetosOpenCsv() throws IOException{
		// TODO Auto-generated method stub
		List<CsvPessoa> pessoas = new ArrayList<>();
        pessoas.add(new CsvPessoa("Joao",35,"joao@yahoo.com.br"));
        pessoas.add(new CsvPessoa("Maria",23,"maria@bol.com.br"));
        pessoas.add(new CsvPessoa("Ana",25,"ana@hotmail.com"));

        Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\administrator\\Documents\\pessoasComoObjeto.csv"));
        StatefulBeanToCsv<CsvPessoa> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        
        try {
			beanToCsv.write(pessoas);
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        writer.flush();
        writer.close();
		
	}

	private static void LerLinhasComoObjetosOpenCsv() throws IOException{
		
		String COMMA_DELIMITER = ",";
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

	private static void escreverCSV() throws IOException{
		// TODO Auto-generated method stub
		String[] cabecalho = {"nome", "idade", "telefone"};
		
		List<String[]> linhas = new ArrayList<>();
		linhas.add(new String[] {"João", "35", "joão@hotmail.com"});
		linhas.add(new String[] {"Ana Maria", "27", "anamaria@gmail.com"});
		linhas.add(new String[] {"Fernando", "33", "fernando@bol.com"});
		
		Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\administrator\\Documents\\pessoas.csv"));
		CSVWriter csvWriter = new CSVWriter(writer);
		
		csvWriter.writeNext(cabecalho);
		csvWriter.writeAll(linhas);
		
		csvWriter.flush();
		writer.close();
	}

	private static void lerCSV() throws IOException{
		// TODO Auto-generated method stub
		Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\administrator\\Documents\\pessoas.csv"));
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		
		List<String[]>pessoas = csvReader.readAll();
		
		 for (String[] pessoa : pessoas)
			 System.out.println(    "Name : " + pessoa [0] +
					            " - Idade : " + pessoa [1] + 
					            " - Email : " + pessoa [2]);
	}
}