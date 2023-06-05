package Program;

import Models.Crud;

import javax.swing.*;

public class Main{
    public static void main(String[] args){
        Crud crud = new Crud();
        crud.selecionarPorMatricula(3);
        String opcaoMenu = "";

        do {
            opcaoMenu = JOptionPane.showInputDialog(null, "CADASTRO DE ALUNOS"
                    +"\n Digite a opção desejada:\n"
                    +"1 - Cadastrar aluno\n"
                    +"2 - Atualizar dados do aluno\n"
                    +"3 - Excluir aluno\n"
                    +"4 - Selecionar por matricula\n"
                    +"5 - Selecionar todos os alunos\n"
                    +"6 - Sair do menu");

            if (opcaoMenu.equals("1")){
                String nome = JOptionPane.showInputDialog(null, "Informe o nome: ");
                String data_matricula = JOptionPane.showInputDialog(null, "Data da matricula: ");
                String vencimento_mensalidade = JOptionPane.showInputDialog(null, "Data de vencimento");
                int serie = Integer.parseInt(JOptionPane.showInputDialog(null, "Serie do aluno: "));
                crud.cadastrarAluno(nome, data_matricula, vencimento_mensalidade, serie);
            }
            else if (opcaoMenu.equals("2")) {
                int numero_matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da matricula: "));
                String nome = JOptionPane.showInputDialog(null, "Informe o nome: ");
                String data_matricula = JOptionPane.showInputDialog(null, "Data da matricula: ");
                String vencimento_mensalidade = JOptionPane.showInputDialog(null, "Data de vencimento");
                int serie = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a serie: "));
                crud.atualizarAluno(numero_matricula, nome, data_matricula, vencimento_mensalidade, serie);
            }
            else if (opcaoMenu.equals("3")) {
                int numero_matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da matricula: "));
                crud.excluirAluno(numero_matricula);
            }
            else if (opcaoMenu.equals("4")) {
                int numero_matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero da matricula: "));
                JOptionPane.showMessageDialog(null, crud.selecionarPorMatricula(numero_matricula));
            }
            else if (opcaoMenu.equals("5")){
                JOptionPane.showMessageDialog(null, crud.selecionarTodosOsAlunos());
            }
        } while (!opcaoMenu.equals("6"));
    }
}
