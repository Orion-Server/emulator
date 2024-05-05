package Orion.Api.Storage.Repository;

import Orion.Api.Storage.Result.IConnectionBooleanResultConsumer;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Api.Storage.Result.IConnectionStatementConsumer;

public interface IDatabaseRepository {
    void select(String query, IConnectionResultConsumer resultConsumer, Object... parameters);

    void updateBatch(String query, IConnectionStatementConsumer statementConsumer, IConnectionResultConsumer resultConsumer);

    void update(String query, IConnectionBooleanResultConsumer consumer, Object... parameters);

    void insertBatch(String query, IConnectionStatementConsumer statementConsumer, IConnectionResultConsumer resultConsumer);

    void insert(String query, IConnectionResultConsumer consumer, Object... parameters);

    void delete(String query, IConnectionBooleanResultConsumer consumer, Object... parameters);
}
