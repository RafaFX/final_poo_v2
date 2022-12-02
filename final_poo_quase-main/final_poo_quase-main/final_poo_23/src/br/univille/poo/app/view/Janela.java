package br.univille.poo.app.view;

import javax.swing.*;

import br.univille.poo.app.entidade.Tarefa;
import br.univille.poo.app.servico.CriaLista;

import br.univille.poo.app.servico.CriarTarefa;

import br.univille.poo.app.servico.ListarTarefas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame {

    private CriaLista criaLista = new CriaLista();
    private JPanel panel;
    private JTextArea campoTextArea;
    private ViewListener listener;

    public Janela(){
        setTitle("Cadastro de Tarefas");
        setSize(800,550);
        configurarJanela();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addListener(ViewListener listener){
        this.listener = listener;
    }

    private void configurarJanela() {
        panel = new JPanel();
        panel.setLayout(null);

        campoTextArea = new JTextArea(40,100);
        campoTextArea.setBounds(50,40,700,90);
        campoTextArea.setBorder(BorderFactory.createLineBorder(getForeground()));



        JLabel label = new JLabel("Tarefa");
        label.setBounds(50,10,100,30);
        label.setOpaque(true);


        JButton salvarBotao = new JButton("Inserir");
        salvarBotao.setBounds(650, 380, 100, 25);
        salvarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // listener.onSalvar();
                Tarefa t = new Tarefa();
                t.setDescricao(campoTextArea.getText());
                t.setConcluido(false);
                CriarTarefa t1 = new CriarTarefa();
                System.out.println(t1);
                try {
                    t1.criar(t);
                    ListarTarefas listarTarefas = new ListarTarefas();
                    for(Tarefa t2 : listarTarefas.obterTodos()){
                    System.out.println(t2);
                    }
                    //chamar novo metodo
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
                popularLista();
            }
        });

        //JLabel label = new JLabel();

        JButton cancelarBotao = new JButton("Excluir");
        cancelarBotao.setBounds(530, 380, 100, 25);
        cancelarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        panel.add(label);
        panel.add(salvarBotao);
        panel.add(cancelarBotao);
        panel.add(campoTextArea);
        popularLista();
        add(panel);
    }

//          ListarTarefas listarTarefas = new ListarTarefas();
//           for(Tarefa t : listarTarefas.obterTodos()){
//             System.out.println(t);
//           }

    private void popularLista(){
        ListarTarefas listarTarefas = new ListarTarefas();
        JPanel panel2 =  new JPanel();
        JScrollPane scrollPanel = new JScrollPane(panel2);
        scrollPanel.setBounds(50,250,500,90);
        for(Tarefa t : listarTarefas.obterTodos()){
            System.out.println("error");
            panel2.add(ItemLista(t));
            panel2.add(Box.createVerticalStrut(20));
        }
        panel.add(scrollPanel);
    }

    private JPanel ItemLista(Tarefa t){
        JPanel panel = new JPanel();
        panel.setBounds(50,250,700,90);
        JLabel label = new JLabel(t.getDescricao());


        JCheckBox CheckBox = new JCheckBox("");
        CheckBox.setSelected(false);

        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(label);
        panel.add(CheckBox);
        return panel;
    }



    interface ViewListener{

        void onSalvar();

    }

}
