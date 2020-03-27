package dev.rastrian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		Boolean errorMsg = true;
		String[] terminalArgs;
		try {
			while(!input.equalsIgnoreCase("sair")){
				input = reader.readLine();
				terminalArgs = input.split(" ");
				if (input.length() > 0 && terminalArgs[0].equals("mips32-decode") &&
					(!terminalArgs[1].isEmpty()) && (!terminalArgs[2].isEmpty()))	{

				}
				if (errorMsg) {
					System.out.println("Erro, utilize a seguinte syntax de execução:\nmips32-decode [input path] [output path]");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
