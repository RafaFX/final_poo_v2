package br.univille.poo.app.persistencia;

import br.univille.poo.app.persistencia.FabricaDeConexoes;

import java.sql.Connection;

class BaseDAO {

    protected Connection obterConexao(){
        return FabricaDeConexoes.obterInstancia().obterConexao();
    }

}
