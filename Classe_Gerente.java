//package funcionarios;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gerente
{
	private String nome;
	private String cpf;
	private LocalDate datadeN;
	//private String endereco;
	private	String senha;

	public Gerente() {}
	public Gerente(String nome, String cpf, String nasc, String senhadoGerente)
	{
		setNome(nome);
		setCpf(cpf);
		setDatadeN(nasc);
    	setSenha(senhadoGerente);
	}
	
	
	

	//Os tipos de retorno das seguintes funções deverão ser alterados para corresponder objetos
	//Para marcar que essa mudança será feita usaremos esse caractere: ¨
	
	public boolean cadastroMedico(String nomeMedico)
	{
		System.out.println("Médico Cadastrado");
		return true;
	}
	
	public boolean buscarMedico(String nomeMedico)
	{
		System.out.println (nomeMedico + " médico encontrado");
		return true;
	}
	
	public boolean buscarMedico(int cfm)
	{
		System.out.println (cfm+" médico encontrado");
		return true;
	}
	
	public void editarMedico()
	{
		System.out.println("Registro editado");
	}
	
	public void removerMedico()
	{
		System.out.println("Registro removido");
	}
	
	public boolean buscarPaciente(String identificacao)
	{
			System.out.println(identificacao + " paciente encontrado");
			return true;
	}
	
	public boolean buscarConsulta(String nome)
	{
		System.out.println("Consulta enonctrada, por paciente ou médico, " + nome);
		return true;
	}
	
	public boolean buscarConsulta (LocalDate dia) 
	{
		System.out.println("Data da consulta: " + dia);
		return true;
	}
	
	public boolean logInGerente(String nome, String senha)
	{
		if (nome == this.nome && senha == this.senha)
		{
			System.out.println("Acesso concedido, bem vindo " + this.nome);
			return true;
		}
		else
		{
			System.out.println("Acesso negado, nome de usuário ou senha incorretos");
			return false;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////
	//Abaixo estam os métodos get e set
	
	
	public void setNome(String nome)
	{
		if (nome != null && nome != "")
		{
			this.nome = nome;
    	}
		else
    	{
			System.out.println("Nome inválido, deve conter caractere");
    	}
	}
	  
	public String getNome()
		{return this.nome;}
	
	public void setCpf(String cpf)
	{
		if (cpf.length() == 14 || cpf.length() == 11)
		{
			this.cpf = cpf;
		}
		else
		{
			System.out.println ("Valor de Cpf Inválido");
		}
	}
	
	public String getCpf()
		{return this.cpf;}
	
	public void setDatadeN(String nasc)
	{
		if (nasc.charAt(3) == '.' || nasc.charAt(3) == '/' || nasc.charAt(3)== '-' || nasc.charAt(2) == '.' || nasc.charAt(2) == '/' || nasc.charAt(2)== '-')
		{
			if ( nasc.charAt(2) == '.' || nasc.charAt(2) == '/' || nasc.charAt(2)== '-' )
			{
				nasc.replace('.', '/');
				nasc.replace('-', '/');
				this.datadeN = LocalDate.parse(nasc, DateTimeFormatter.ofPattern("d/mm/yyyy"));
			}
			
			nasc.replace('.', '/');
			nasc.replace('-', '/');
			
			this.datadeN = LocalDate.parse(nasc, DateTimeFormatter.ofPattern("dd/mm/yyyy"));
		}
	}
	
	public LocalDate getDatadeN()
		{return this.datadeN;}
	
	public void setSenha(String senha)
	{
		if (senha != null && senha != "" && senha.length() > 3)
		{
			this.senha = senha;
    	}
		else
    	{
			System.out.println("Senha inválida, deve conter mais de 3 caracteres");
    	}
	}
	
	public String getSenha()
		{return this.senha;}
}
