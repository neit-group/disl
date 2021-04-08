package org.disl.db.oraclewallet

import org.disl.db.oracle.OracleReverseEngineeringService
import org.disl.db.oracle.OracleSchema
import org.disl.db.reverseEngineering.ReverseEngineeringService
import org.disl.meta.Mapping
import org.disl.meta.PhysicalSchema

import java.sql.SQLException

class OraclewalletSchema extends PhysicalSchema{

    String walletLocation
    String jdbcDriver="oracle.jdbc.OracleDriver"

    @Override
    public void init() {
        super.init();
        if (!getJdbcUrl()) {
            walletLocation=getSchemaProperty("walletLocation", walletLocation)
            setServiceName(getSchemaProperty("serviceName"))
            setJdbcUrl("jdbc:oracle:thin:@${getDatabaseName()}?TNS_ADMIN=${getWalletLocation()}")
        }
    }

    public void setServiceName(String serviceName) {
        if (serviceName!=null) {
            setDatabaseName("${serviceName}")
        }
    }

    @Override
    public String evaluateExpressionQuery(String expression) {
        "SELECT ${expression} FROM dual"
    }

    @Override
    public String evaluateConditionQuery(String expression) {
        "select 1 from dual where ${expression}"
    }

    @Override
    public String getRecordQuery(int index,String expressions) {
        "select ${index} as DUMMY_KEY,${expressions} from dual"
    }

    public void validateQuery(Mapping mapping) throws AssertionError {
        try {
            getSql().execute(getValidationQuery(mapping))
        } catch (SQLException e) {
            throw new AssertionError("Validation failed with message: ${e.getMessage()} for query:\n${getValidationQuery(mapping)}")
        }
    }

    @Override
    protected String getValidationQuery(Mapping mapping) {
        """\
declare
${OracleSchema.getBindVariablesDeclaration(mapping)} 
\tcursor c is ${mapping.getSQLQuery()}; 
begin 
\tnull; 
end;"""
    }

    public static String getBindVariablesDeclaration(Mapping mapping) {
        mapping.getBindVariables().collect({"""\
\t${it.name} ${it.dataType}:=${it.value};
"""}).join()
    }

    @Override
    protected String getEvaluateRowCountQuery(String sqlQuery) {
        "select count(1) from (${sqlQuery})"
    }

    @Override
    public ReverseEngineeringService getReverseEngineeringService() {
        return new OracleReverseEngineeringService();
    }
}
