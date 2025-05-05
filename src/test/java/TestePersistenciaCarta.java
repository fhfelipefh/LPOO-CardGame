import br.edu.seuprojeto.lpoo.cardgame.control.PersistenciaJPA;
import br.edu.seuprojeto.lpoo.cardgame.model.Carta;
import br.edu.seuprojeto.lpoo.cardgame.model.Categoria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestePersistenciaCarta {

    private PersistenciaJPA jpa;

    @Before
    public void setUp() {
        jpa = new PersistenciaJPA();
        System.out.println("Iniciando teste de persistência...");
    }

    @After
    public void tearDown() {
        if (jpa != null && jpa.conexaoAberta()) {
            jpa.fecharConexao();
            System.out.println("Conexão fechada com sucesso.");
        }
    }

    @Test
    public void cadastroCartas() {
        try {
            Carta c = new Carta();
            c.setNome("Escudo Ferro");
            c.setAtaque(0);
            c.setDefesa(50);
            c.setCategoria(Categoria.ARMADURA);
            c.setRaridade(2);
            jpa.persist(c);
        } catch (Exception ex) {
            System.err.println("Erro ao persistir Carta: " + ex);
        }

        ArrayList<Carta> listaCartas = (ArrayList<Carta>) jpa.getCartas();
        if (listaCartas.isEmpty()) {
            System.out.println("Não há cartas cadastradas");
        } else {
            for (Carta c : listaCartas) {
                System.out.println(c.getNome() + " - ataque: " + c.getAtaque() + " - defesa: " + c.getDefesa());
            }
        }
    }
}