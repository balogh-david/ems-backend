package hu.bdavid.ems.app.dto;


import hu.bdavid.ems.app.enums.Permission;

/**
 * Adott felhasználó csoport darabszámait reprezentáló osztály.
 *
 * @author balogh.david
 */
public interface DashboardResponse {

    /**
     * A jogosultság darabszáma.
     */
    int getCount();

    /**
     * A Jogosultság.
     */
    Permission getPermission();
}
