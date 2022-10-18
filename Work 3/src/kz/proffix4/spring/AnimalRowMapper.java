package kz.proffix4.spring;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Animal из считанной записи таблицы БД
 *
 */
public class AnimalRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        AnimalResultSetExtractor extractor = new AnimalResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class AnimalResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Animal animal = new Animal();
            animal.setId(rs.getInt("id"));
            animal.setNickName(rs.getString("NickName"));
            animal.setAnimalKind(rs.getString("AnimalKind"));
            animal.setAge(rs.getInt("AnimalAge"));
            return animal;
        }
    }
}
