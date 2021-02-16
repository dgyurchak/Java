package hu.unideb.inf.duogame;

import java.util.TreeMap;

public interface DuoGame {
    /**
     * Megadja a játékállást leíró <code>status</code> tömbben az összefüggő szabad területek kezdőpozícióját és hosszát.
     * A metódus nem módosíthatja a paramétereként kapott tömböt!
     *
     * @param status A játék egy lehetséges állása. A tömb 0-s indexű elemét nem használjuk, az abban tárolt érték
     *               lényegtelen a játék szempontjából.
     *
     * @return Egy rendezett táblázatban visszaadja az összefüggő szabad területeket. A táblázat elemeinek kulcs része a
     * tömb egy olyan indexe, ahol egy szabad terület kezdődik. Az érték rész pedig az adott szabad terület hosszát adja
     * meg elemszámban kifejezve. A szabad cellákat a tömbben a FREE érték jelöli.
     */
    TreeMap<Integer, Integer> freeCells(Status[] status);

    /**
     * Megadja, hogy egy lépésben befejezhető-e a játék, azaz eldönti, hogy pontosan egy olyan összefüggő szabad
     * cellasor van-e a tömbben, amelynek a hossza 1 vagy 2 cella.
     * A metódus nem módosíthatja a paramétereként kapott tömböt!
     *
     * @param status A játék egy lehetséges állása. A tömb 0-s indexű elemét nem használjuk, az abban tárolt érték
     *               lényegtelen a játék szempontjából.
     *
     * @return <code>true</code>, ha a játék egy lépésben befejezhető, különben <code>false</code>.
     */
    boolean finishedInNextMove(Status[] status);

    /**
     * Megteszi a <code>player</code> játékos lépését, azaz visszaad egy olyan új tömböt, amelyben a <code>start</code>
     * indexű mezőtől kezdve <code>length</code> darab mezőt <code>player</code> értékűre állít. Feltehető, hogy a lépés
     * paraméterei mindig helyesek, a lépés mindig biztosan végrehajtható.
     * A metódus nem módosíthatja a paramétereként kapott tömböt!
     *
     * @param status A játék egy lehetséges állása. A tömb 0-s indexű elemét nem használjuk, az abban tárolt érték
     *               lényegtelen a játék szempontjából.
     * @param player A lépő játékos (ALICE vagy BOB).
     * @param start A <code>status</code> tömb egy indexe, ahol az a szabad terület kezdődik, amelyet a lépő játékos
     *              el szeretne foglalni.
     * @param length Az elfoglalni kívánt szabad terület hossza elemszámban kifejezve. Értéke 1 vagy 2 lehet.
     *
     * @return Egy új tömböt ad vissza, amely a játék állását reprezentálja a lépés végrehajtását követően.
     */
    Status[] applyMove(Status[] status, Status player, int start, int length);
}
