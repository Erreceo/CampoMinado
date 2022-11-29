package view

import model.Tabuleiro
import model.TabuleiroEvento
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    TelaPrincipal()
}

class TelaPrincipal : JFrame() {
    private val tabuleiro = Tabuleiro(qtdeLinhas = 16, qtdeColunas = 30, qtdeMinas = 89)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro);

    init {
        tabuleiro.onEvento(this::mostrarResultado)
        add(painelTabuleiro)
        setSize(690, 438)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Campo Minado"
        isVisible = true
    }

    private fun mostrarResultado(evento: TabuleiroEvento) {
        SwingUtilities.invokeLater {
            val msg = when (evento) {
                TabuleiroEvento.VITORIA -> "Voçê ganhou!"
                TabuleiroEvento.DERROTA -> "Voçê perdeu.. :P"
            }
            JOptionPane.showMessageDialog(this, msg)
            tabuleiro.reinicializar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }
}