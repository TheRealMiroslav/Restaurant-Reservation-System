package org.mns.ui.command;

/**
 * Funkcionální rozhraní pro zapouzdření akce spustitelné z menu.
 * Implementuje návrhový vzor Command.
 */
@FunctionalInterface
public interface Command {
    /**
     * Provede konkrétní akci příkazu.
     */
    void execute();
}