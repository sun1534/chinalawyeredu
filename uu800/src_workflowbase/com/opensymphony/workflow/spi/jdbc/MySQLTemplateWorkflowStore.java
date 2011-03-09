package com.opensymphony.workflow.spi.jdbc;

import org.springframework.dao.DataAccessException;

import java.util.Properties;

/**
 * @author 
 */
public class MySQLTemplateWorkflowStore extends JDBCTemplateWorkflowStore {

    protected String entrySequenceIncrement = null;
    protected String entrySequenceRetrieve = null;
    protected String stepSequenceIncrement = null;
    protected String stepSequenceRetrieve = null;

    public void setJdbcTemplateProperties(Properties jdbcTemplateProperties) {
        this.jdbcTemplateProperties = jdbcTemplateProperties;
        super.init();
        this.init();
    }

    public void init() {
        stepSequenceIncrement = getInitProperty("step.sequence.increment", "INSERT INTO OS_STEPIDS (ID) values (null)");
        stepSequenceRetrieve = getInitProperty("step.sequence.retrieve", "SELECT max(ID) FROM OS_STEPIDS");
        entrySequenceIncrement = getInitProperty("entry.sequence.increment", "INSERT INTO OS_ENTRYIDS (ID) values (null)");
        entrySequenceRetrieve = getInitProperty("entry.sequence.retrieve", "SELECT max(ID) FROM OS_ENTRYIDS");
    }

    protected long getNextEntrySequence() throws DataAccessException {

        this.getJdbcTemplate().update(entrySequenceIncrement);

        long id = this.getJdbcTemplate().queryForLong(entrySequenceRetrieve);

        return id;

    }

    protected long getNextStepSequence() throws DataAccessException {


        this.getJdbcTemplate().update(stepSequenceIncrement);

        long id = this.getJdbcTemplate().queryForLong(stepSequenceRetrieve);

        return id;
    }
}
