package han.ica.dea.persistence.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.dto.TracksDto;

import java.net.URL;

public class CacheHelper {

    private static CacheHelper instance = new CacheHelper();
    private CacheManager cacheManager;
    
    public static enum CacheAlias {
        SESSION,
        TRACKS,
        PLAYLISTS,
        TRAKCS_NOT_IN_PLAYLIST
    }

    private CacheHelper() {
        URL url = getClass().getClassLoader().getResource("cacheConfig");
        Configuration config = new XmlConfiguration(url);
        cacheManager = CacheManagerBuilder.newCacheManager(config);
        cacheManager.init();
    }

    public static CacheHelper get() {
        return instance;
    }

    public Cache create(CacheAlias type) {
        switch (type) {
            case SESSION:
                return cacheManager.getCache("session", String.class, LoginResponseDto.class);
            case TRACKS:
            	return cacheManager.getCache("tracks", Integer.class, TracksDto.class);
        }
        
        return null;
    }

}