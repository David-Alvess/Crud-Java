package Models;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Crud {

    Connection conectarBancoDeDados;
    PreparedStatement consulta;

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public Crud(){
        try {
            conectarBancoDeDados = DriverManager.getConnection("jdbc:mysql://localhost/base de alunos", "root", "");
            //System.out.println("Deu certo!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexao do banco de dados! Motivo: " +e.getMessage());
        }
    }
    public void cadastrarAluno(String nome, String data_matricula, String vencimento_mensalidade, int serie){
        try{
            consulta = conectarBancoDeDados.prepareStatement("INSERT INTO aluno (nome, data_matricula, vencimento_mensalidade, serie) VALUES (?,?,?,?)");
            consulta.setString(1, nome);
            consulta.setDate(2, new java.sql.Date(formatarData.parse(data_matricula).getTime()));
            consulta.setDate(3, new java.sql.Date(formatarData.parse(vencimento_mensalidade).getTime()));
            consulta.setInt(4, serie);
            consulta.executeUpdate();

            System.out.println("Cadastro realizado com sucesso!");

        } catch (SQLException | ParseException e) {
            throw new RuntimeException("Erro para cadastrar! Motivo: " +e.getMessage());
        }
    }

    public String selecionarTodosOsAlunos(){
        try {
            consulta = conectarBancoDeDados.prepareStatement("SELECT * FROM aluno");
            ResultSet resultadoConsulta = consulta.executeQuery();

            String relatorio = "MATRICULA\t NOME\t DATA MATRICULA\t VENC. MENSALIDADE\t SERIE\n";

            while (resultadoConsulta.next()){
                relatorio += resultadoConsulta.getString("numero_matricula") +"\t\t\t"
                        + resultadoConsulta.getString("nome") +"\t\t"
                        + resultadoConsulta.getString("data_matricula") +"\t\t"
                        + resultadoConsulta.getString("vencimento_mensalidade") +"\t\t\t"
                        + resultadoConsulta.getString("serie") +"\n";
            }
            System.out.println(relatorio);
            return relatorio;

        } catch (Exception e){
            System.out.println("Erro para ver todos os alunos! Motivo: " +e.getMessage());
        }
        return null;
    }
    public void atualizarAluno(int numero_matricula, String nome, String data_matricula, String vencimento_mensalidade, int serie){
        try{
            consulta = conectarBancoDeDados.prepareStatement("UPDATE aluno SET" +
                    " nome = ?, data_matricula = ?, vencimento_mensalidade = ?, serie = ?" +
                    " where numero_matricula = ?");

            consulta.setString(1, nome);
            consulta.setDate(2, new java.sql.Date(formatarData.parse(data_matricula).getTime()));
            consulta.setDate(3, new java.sql.Date(formatarData.parse(vencimento_mensalidade).getTime()));
            consulta.setInt(4, serie);
            consulta.setInt(5, numero_matricula);
            consulta.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!");

        } catch (SQLException | ParseException e) {
            throw new RuntimeException("Erro para atualizar aluno! Motivo: " +e.getMessage());
        }
    }
    
    public void excluirAluno(int numero_matricula){
        try{
            consulta = conectarBancoDeDados.prepareStatement("DELETE FROM aluno where numero_matricula = ?");
            consulta.setInt(1, numero_matricula);
            consulta.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Erro para excluir aluno! Motivo: " +e.getMessage());
        }
    }

    public String selecionarPorMatricula(int numero_matricula){
        try {
            consulta = conectarBancoDeDados.prepareStatement("SELECT * FROM aluno WHERE numero_matricula = " +numero_matricula);
            ResultSet resultadoConsulta = consulta.executeQuery();

            String relatorio = "MATRICULA\t NOME\t DATA MATRICULA\t VENC. MENSALIDADE\t SERIE\n";

            while (resultadoConsulta.next()){
                relatorio += resultadoConsulta.getString("numero_matricula") +"\t\t\t"
                        + resultadoConsulta.getString("nome") +"\t\t"
                        + resultadoConsulta.getString("data_matricula") +"\t\t"
                        + resultadoConsulta.getString("vencimento_mensalidade") +"\t\t\t"
                        + resultadoConsulta.getString("serie") +"\n";
            }
            System.out.println(relatorio);
            return relatorio;

        } catch (Exception e){
            System.out.println("Erro para ver todos os alunos! Motivo: " +e.getMessage());
        }
        return null;
    }
}
