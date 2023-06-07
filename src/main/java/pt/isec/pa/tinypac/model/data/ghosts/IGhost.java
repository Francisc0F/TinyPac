package pt.isec.pa.tinypac.model.data.ghosts;

public interface IGhost {
    void savePosition() throws CloneNotSupportedException;
    boolean getIsVulnerable();
}
