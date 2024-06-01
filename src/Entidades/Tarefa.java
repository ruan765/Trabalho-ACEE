package Entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tarefa implements Comparable<Tarefa> {
	Scanner scan = new Scanner(System.in);

	private Integer Id;
	private String nome;
	private String descricao;
	private LocalDate prazo;
	private Integer prioridade;
	
	
	public Tarefa(Integer id, String nome, String descricao, LocalDate prazo, Integer prioridade) {
		Id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.prazo = prazo;
		this.prioridade = prioridade;
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public LocalDate getPrazo() {
		return prazo;
	}


	public void setPrazo() {
		
		while(true) {	
			try {
				LocalDate NewPrazo = LocalDate.parse(scan.next() , DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				if(NewPrazo.isAfter(LocalDate.now())) {
					this.prazo = NewPrazo;
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
		
	}


	public Integer getPrioridade() {
		return prioridade;
	}


	public void setPrioridade() {
		while(true) {	
			try {
				int prioridade = scan.nextInt();
				if(prioridade > 0 && prioridade <=5) {
					this.prioridade = prioridade;
					break;
				}
				
				throw new InputMismatchException();
				
				
				
			}
			catch(InputMismatchException e) {
				System.out.println("Prioridade invalida:");
				System.out.println("Digite uma nova prioridade: (1-5)");
			}

		}
	}


	@Override
	public String toString() {
		return String.format("Tarefa [Id=%s, nome=%s, descricao=%s, prazo=%s, prioridade=%s]", Id, nome, descricao,
				prazo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), prioridade);
	}


	@Override
	public int compareTo(Tarefa outraTarefa) {
		
		return prioridade.compareTo(outraTarefa.getPrioridade());
	}
	
	public int compareToDecrecente(Tarefa outraTarefa) {
		return outraTarefa.getPrioridade().compareTo(prioridade);
	}
	
	
	
	
	
	
	
	
	

}
