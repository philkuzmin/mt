package dao.h2;

import dao.InstrumentDao;
import model.Instrument;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iMac on 09/03/17.
 */
public class H2InstrumentDao implements InstrumentDao {

    private DataSource dataSource;

    private final String CREATE_INSTRUMENT_SQL = "INSERT INTO Instruments (instrument_name) VALUES (?);";

    private final String READ_INSTRUMENT_SQL = "SELECT instrument_name FROM Instruments WHERE instrument_id = ?";

    private final String GET_USER_INSTRUMENTS_SQL =
            "SELECT i.instrument_id, i.instrument_name " +
                    "FROM Instruments AS i INNER JOIN Users_Instruments AS u " +
                    "ON i.instrument_id = u.instrument_id " +
                    "WHERE u.user_id = ?";

    public H2InstrumentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createInstruments(Instrument[] instruments) {
        int[] result = {0};
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(CREATE_INSTRUMENT_SQL)) {
            for (Instrument instrument: instruments) {
                statement.setString(1, instrument.getInstrumentName());
                statement.addBatch();
            }
            result = statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("createInstrument() - " + e.getMessage());
        }
        return result.length;
    }

    @Override
    public Instrument readInstrument(long instrumentId) {
        Instrument instrument = new Instrument();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(READ_INSTRUMENT_SQL)) {
            statement.setLong(1, instrumentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                instrument.setInstrumentName(resultSet.getString("instrument_name"));
                instrument.setInstrumentId(instrumentId);
            }
        } catch (SQLException e) {
            System.out.println("readInstrument() - " + e.getMessage());
        }
        return instrument;
    }

    @Override
    public void updateInstrument(Instrument instrument) throws SQLException {

    }

    @Override
    public void deleteInstrument(long instrumentId) throws SQLException {

    }

    @Override
    public List<Instrument> getUserInstruments(long userId) {
        List<Instrument> instruments = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(GET_USER_INSTRUMENTS_SQL)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Instrument instrument = new Instrument();
                    instrument.setInstrumentId(resultSet.getLong("instrument_id"));
                    instrument.setInstrumentName(resultSet.getString("instrument_name"));
                    instruments.add(instrument);
                }
            }
        } catch (SQLException e) {
            System.out.println("getUserInstruments() - " + e.getMessage());
        }
        return instruments;
    }
}