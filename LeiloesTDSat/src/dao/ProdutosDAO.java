package dao;

import beans.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto(ProdutosDTO produto){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            st.setString(1, produto.getNome());
            st.setDouble(2,produto.getValor());
            st.setString(3, produto.getStatus());
            
            status = st.executeUpdate();
            
            System.out.println("Produto salvo com sucesso");
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

