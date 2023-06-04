package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.Map;


/**
 * Este fantasma desloca-se na direção de um dos cantos do labirinto de acordo com a
 * seguinte ordem: canto superior direito, canto inferior direito, canto superior esquerdo,
 * canto inferior esquerdo, retomando novamente a sequência. Ao chegar a um cruzamento,
 * sorteia uma direção que o faça, no imediato, aproximar-se mais do canto pretendido do
 * labirinto. Quando encontra um obstáculo, tendo de sortear uma nova direção, se já está a
 * menos de uma determinada distância do canto pretendido, então altera o objetivo para o
 * próximo canto do labirinto. A distância referida deve ser definida pelo aluno - por exemplo
 * 10-15% da largura do tabuleiro;
 */
public class Pinky extends Ghost {
    public static final char SYMBOL = '&';
    public Pinky(Map map, Map.Position p) {
        super(map, p);
        savePosition();
    }

    @Override
    public void evolve() {
        if(!this.map.ghostsEnabled()){
            return;
        }

        if(isVulnerable){
            Map.Position next = previousMoves.pop();
            moveGhost(next.y(), next.x());
            if(previousMoves.size() == 0){
                isVulnerable = false;
            }
            return;
        }

        direction = getNextDirection();
        switch (direction) {
            case UP -> this.up();
            case DOWN -> this.down();
            case RIGHT -> this.right();
            case LEFT -> this.left();
        }
        savePosition();
    }

    @Override
    public char getSymbol() {
        return isVulnerable ? VULNERABLE : SYMBOL;
    }
}
