package org.puprleteam.interfaces;

import org.puprleteam.pojos.Tracking;

import java.io.Serializable;
import java.util.List;

public interface TrackingService {

    /**
     * Создает новый трек
     * @param tracking - трек для создания
     */
    Serializable create(Trackings tracking);


    /**
     * Возвращает список всех имеющихся треков
     * @return список треков
     */
    List<Tracking> readAll();

    /**
     * Возвращает трек по его ID
     * @param id - ID трека
     * @return - объект трека с заданным ID
     */

    Tracking read(Long id);

    /**
     * Обновляет трек с заданным ID,
     * в соответствии с переданным треком
     * @param tracking - трек в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */

    boolean update(Tracking tracking);

    /**
     * Удаляет трек с заданным ID
     * @param id - id трека, которого нужно удалить
     * @return - true если трек был удален, иначе false
     */
    boolean delete(Long id);
}
