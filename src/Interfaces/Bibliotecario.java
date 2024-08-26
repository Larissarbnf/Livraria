package Interfaces;

import DTO.AluguelDTO;
import DTO.BibliotecarioDTO;
import DTO.RelatorioDTO;

import java.sql.SQLException;
import java.util.List;

public interface Bibliotecario {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String mensagem);

    void create(BibliotecarioDTO bibliotecario) throws SQLException;
    BibliotecarioDTO getBibliotecarioById(long id) throws SQLException;
    BibliotecarioDTO login(String email, String senha) throws SQLException;
    List<BibliotecarioDTO> getAllBibliotecarios() throws SQLException;
    void updateBibliotecario(BibliotecarioDTO bibliotecario) throws SQLException;
    void deleteBibliotecario(long id) throws SQLException;

    void registrarAluguel(AluguelDTO aluguel) throws SQLException;
    void devolverLivroAlugado(long aluguelId) throws SQLException;
    List<RelatorioDTO> gerarRelatorioLivrosVendidos() throws SQLException;
    List<RelatorioDTO> gerarRelatorioLivrosAlugados() throws SQLException;
    List<RelatorioDTO> gerarRelatoriosAluguel() throws SQLException; 
}
