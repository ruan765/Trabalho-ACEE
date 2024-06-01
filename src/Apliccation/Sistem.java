package Apliccation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import Entidades.Tarefa;

public class Sistem {
	Scanner sc = new Scanner(System.in);

	private List<Tarefa> tarefas = new ArrayList<>();
	
	public Sistem() {
		
	}
	
	public void printarTafs() {
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			limparTerminal();
			System.out.println("");
			System.out.println("Tarefas registradas: ");
			System.out.println("");
				for(Tarefa i: tarefas) {
					System.out.println(i.toString());
				}
			System.out.println("");
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas:");
		}
			
	}
	
	public void ProgramBanner(){
		System.out.println("#################################################");
		System.out.println("      Sistema de Gerenciamento de Tarefas        ");
		System.out.println("#################################################");
		System.out.println("1 - Adicionar uma Tarefa");
		System.out.println("2 - Remover uma Tarefa");
		System.out.println("3 - Filtrar tarefa por prioridade");
		System.out.println("4 - Ver as tarefas");
		System.out.println("5 - Salvar tarefas");
		System.out.println("6 - Modificar Tarefa");
		System.out.println("7 - Sair");
		System.out.println("#################################################");
	}


	public void AdicionarTarefa() {
		
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		int id = 0;
		System.out.println("");
		System.out.println("Registro de Tarefa -  ");
		
		
		if(tarefas.isEmpty()) {
			id = 1;
		}
		
		else {
			id = tarefas.size() + 1;
		}
		
		System.out.print("    Digite o nome da Tarefa: ");	
		String nome = sc.next();
		System.out.print("    Descrição: ");
		sc.nextLine();
		String descricao = sc.nextLine();
		System.out.print("    Prazo: (dd/mm/yyyy) ");
		LocalDate prazo = null;
		
			while(true) {	
				try {
					prazo = LocalDate.parse(sc.next() , fmt1);
					if(prazo.isAfter(LocalDate.now())) {
						 break;
					}
					System.out.println("Data invalida:");
					System.out.println("Digite uma nova data: (dd/mm/yyyy)");
					
				}
				catch(DateTimeParseException e) {
					System.out.println("Data invalida:");
					System.out.println("Digite uma nova data: (dd/mm/yyyy)");
				}
	
			}
			
		System.out.print("    Prioridade: (1-5) ");
		
		int prioridade = 0;
		while(true) {	
			try {
				prioridade = sc.nextInt();
				if(prioridade > 0 && prioridade <=5) {
					break;
				}
				
				throw new InputMismatchException();
				
				
			}
			catch(InputMismatchException e) {
				System.out.println("Prioridade invalida:");
				System.out.println("Digite uma nova prioridade: (1-5)");
			}

		}
	
		limparTerminal();
		tarefas.add(new Tarefa(id , nome, descricao, prazo, prioridade));
	}
	
	public void RemoverTarefa() {
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			System.out.println("");
			System.out.println("Tarefas registradas: ");
			System.out.println("");
			printarTafs();
			System.out.println("");
			System.out.print("    Digite o ID da tarefa que deseja remover:");
			int id = sc.nextInt();
			tarefas.removeIf(i ->i.getId() == id );
			limparTerminal();
			System.out.println("Tarefa com o ID " + id + " removida! ");
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas:");
		}
		
		
		
	}

	public void modificarTarefa(){
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			boolean encontrada =  false;
			System.out.println("Tarefas registradas: ");
			printarTafs();
			System.out.print("Digite o ID da tarefa que deseja modificar: ");
			int id = sc.nextInt();
			for(Tarefa taf : tarefas) {
				if (taf.getId() == id) {
					
					limparTerminal();
	                encontrada = true;
	                System.out.println("Tarefa encontrada:");
	                System.out.println(taf);
					System.out.println("\n");

	                System.out.println("O que você deseja modificar?");
	                System.out.println("1. Nome da Tarefa");
	                System.out.println("2. Descrição");
	                System.out.println("3. Prazo");
	                System.out.println("4. Prioridade");
					System.out.println("");
					System.out.print("Opção: ");
	                int opcao = sc.nextInt();
					System.out.println("");	

	                switch (opcao) {
	                    case 1:
	                        System.out.print("Digite o novo nome da Tarefa: ");
	                        taf.setNome(sc.next());
	                        break;
	                    case 2:
	                        System.out.print("Digite a nova descrição: ");
	                        sc.nextLine(); 
	                        taf.setDescricao(sc.nextLine());
	                        break;
	                    case 3:
	                        System.out.print("Digite o novo prazo: (dd/mm/yyyy) ");
	                        taf.setPrazo();
	                        break;
	                    case 4:
	                        System.out.print("Digite a nova prioridade: (1-5) ");
	                        taf.setPrioridade();
	                        break;
	                    default:
	                        System.out.println("Opção inválida!");
	                        break;
	                }
	                limparTerminal();
	                System.out.println("Tarefa Modificada!");
	                break;
	            }
	        }

	        if (!encontrada) {
	            System.out.println("Tarefa não encontrada!");
	        }
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas.");
		}
		
	}
	
	public void filtroPrioridade() {
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			System.out.println("");
			System.out.print("Digite o numero de prioridade: ");
			int prioridade =  sc.nextInt();
			limparTerminal();
			System.out.println("");
			for(Tarefa i: tarefas) {
				if(i.getPrioridade() == prioridade) {
					System.out.println(i);
				}
			}
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas:");
		}
		
		
	}
		
	public void ordenarCrescente() {
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			Collections.sort(tarefas);
			
			for(Tarefa taf:tarefas) {
				System.out.println(taf);
			}
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas:");
		}
		
	}
	
	File CaminhoArquivo = null;
	public void salvar() {
		
		try {
			
			if(tarefas.isEmpty()) {
				throw new Exception();
			}
			
			limparTerminal();
			System.out.println("");
	        System.out.println("Tarefas salvas na Área de Trabalho!");
	        System.out.println("");
	        String desktop = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
	        String nomeArquivo = "Tarefas.txt";
	        String caminhoFinal = desktop + File.separator + nomeArquivo;
	        File file = new File(caminhoFinal);
	        CaminhoArquivo = file.getAbsoluteFile();

	        try(BufferedWriter bf =  new BufferedWriter(new FileWriter(CaminhoArquivo))) {
	            for(Tarefa tafs: tarefas) {
	                bf.write(tafs.toString());
	                bf.newLine();
	            }
	        }

	        catch(IOException e) {
	            System.out.println("ERROR:" + e.getMessage());
	        }
			
		}
		
		catch(Exception e) {
			System.out.println("Não há tarefas registradas:");
		}
		
		
	}
	
	public void load() {
		try(BufferedReader br = new BufferedReader(new FileReader(CaminhoArquivo))) {
			
			String line = br.readLine();
			while(line != null) {
				String[] separado = line.split(",");
				String[] correto = null;
				String[] informacoes = null;
				for(int i = 0; i<=separado.length; i++) {
					correto[i] = separado[i];
					for(int j = 0;j<=1; j++) {
						informacoes[i] = correto[1];
						
						
						
					}
					
					
				}
				
			}
			
		}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void limparTerminal() {
        try {
            new ProcessBuilder("powershell", "-Command", "Clear-Host").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.err.println("Erro ao limpar o terminal: " + e.getMessage());
        }
        
        
    }

}