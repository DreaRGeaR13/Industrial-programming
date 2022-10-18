package kz.proffix4.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Animal
 *
 */
public class AnimalDAO implements IAnimalDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Animal animal) { // Реализация вставки новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO animal (NickName, AnimalKind, AnimalAge) VALUES(?,?,?)",
                new Object[]{animal.getNickName(), animal.getAnimalKind(), animal.getAge()});
    }

    @Override
    public void append(String NickName, String AnimalKind, int AnimalAge) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO animal (NickName, AnimalKind, AnimalAge) VALUES(?,?,?)",
                new Object[]{NickName, AnimalKind, AnimalAge});
    }

    @Override
    public void deleteByAnimalKind(String AnimalKind) {  // Реализация удаления записей по виду животного
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM animal WHERE AnimalKind LIKE ?", new Object[]{'%' + AnimalKind + '%'});
    }
    
    @Override
    public void deleteByAge(int AnimalAge) {  // Реализация удаления записей по возрасту животного
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM animal WHERE AnimalAge LIKE ?", new Object[]{'%' + AnimalAge + '%'});
    }
    
    @Override
    public void deleteByNickName(String NickName) {  // Реализация удаления записей по кличке
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM animal WHERE NickName LIKE ?", new Object[]{'%' + NickName + '%'});
    }

    
    @Override
    public void delete(final String NickName, final String AnimalKind) {  // Реализация удаления записей с указанным именем и видом
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from animal where NickName= ? AND AnimalKind = ?", new Object[]{NickName, AnimalKind});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
    
      @Override
    public void deleteByNickAge(final String NickName, final int AnimalAge) {  // Реализация удаления записей с указанной кличкой и возрастом
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from animal where NickName= ? AND AnimalAge = ?", new Object[]{NickName, AnimalAge});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
    @Override
    public void deleteByAll(final String NickName, final String AnimalKind, final int AnimalAge) {  // Реализация удаления записей с указанным именем и видом и возрастом
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from animal where NickName= ? AND AnimalKind = ? AND AnimalAge = ?", new Object[]{NickName, AnimalKind, AnimalAge});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
    


    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from animal");
    }

    @Override
    public void update(String oldAnimalAge, String newAnimalAge) {  // Изменение записей возраста животных в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE animal SET AnimalAge = ? WHERE AnimalAge = ?", new Object[]{newAnimalAge, oldAnimalAge});
    }

    @Override
    public Animal findByAge(int age) { // Реализация поиска записей по возрасту
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Animal> animal = jt.query("SELECT * FROM animal WHERE AnimalAge = ?",
                new Object[]{age}, new AnimalRowMapper());
        return animal.size() > 0 ? animal.get(0) : null;
    }

    @Override
    public List<Animal> findByNickName(String NickName) {  // Реализация поиска записей по кличке
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM animal WHERE NickName LIKE ?";
        List<Animal> animals = jt.query(sql, new Object[]{'%' + NickName + '%'}, new AnimalRowMapper());
        return animals;
    }

    @Override
    public List<Animal> findByAnimalKind(String AnimalKind) {  // Реализация поиска записей по виду
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM animal WHERE AnimalKind LIKE ?";
        List<Animal> animals = jt.query(sql, new Object[]{'%' + AnimalKind + '%'}, new AnimalRowMapper());
        return animals;
    }
    
    

    @Override
    public List<Animal> select(String NickName, String AnimalKind) {  // Реализация получения записей с заданной кличко и видом
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from animal where NickName = ? AND AnimalKind= ?",
                new Object[]{NickName, AnimalKind}, new AnimalRowMapper());
    }

    @Override
    public List<Animal> selectAll() {  // Реализация получения всех записей
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from animal", new AnimalRowMapper());
    }

    
}
