package pers.dlx.parser;

public enum Scope {

    // SQL object scope
    SQL_SCOPE_FREE,
    SQL_SCOPE_PROC,
    SQL_SCOPE_FUNC,
    SQL_SCOPE_TRIGGER,
    SQL_SCOPE_TABLE,
    SQL_SCOPE_TEMP_TABLE,
    SQL_SCOPE_INDEX,
    SQL_SCOPE_TABLESPACE,

    // Procedural block types
    SQL_BLOCK_PROC,
    SQL_BLOCK_IF,
    SQL_BLOCK_WHILE,
    SQL_BLOCK_HANDLER,
    SQL_BLOCK_CASE,
    SQL_BLOCK_FOR,
    SQL_BLOCK_LOOP,
    SQL_BLOCK_CHECK,
    SQL_BLOCK_REPEAT,
    SQL_BLOCK_BEGIN,
    SQL_BLOCK_EXCEPTION,
    SQL_BLOCK_TRY,
    SQL_BLOCK_CATCH,

    // Boolean expression scope
    SQL_BOOL_IF,
    SQL_BOOL_IF_EXP,
    SQL_BOOL_CASE,
    SQL_BOOL_CHECK,
    SQL_BOOL_JOIN_ON,
    SQL_BOOL_WHERE,
    SQL_BOOL_TRIGGER_WHEN,
    SQL_BOOL_WHILE,
    SQL_BOOL_EXISTS,
    SQL_BOOL_UNTIL,
    SQL_BOOL_HAVING,
    SQL_BOOL_CREATE_RULE,
    SQL_BOOL_ASSIGN,

    // SQL clause scope
    SQL_SCOPE_ASSIGNMENT_RIGHT_SIDE,
    SQL_SCOPE_CASE_FUNC,
    SQL_SCOPE_CURSOR_PARAMS,
    SQL_SCOPE_FUNC_PARAMS,
    SQL_SCOPE_INSERT_VALUES,
    SQL_SCOPE_PROC_PARAMS,
    SQL_SCOPE_SELECT_STMT,
    SQL_SCOPE_TAB_COLS,
    SQL_SCOPE_TRG_WHEN_CONDITION,
    SQL_SCOPE_VAR_DECL,
    SQL_SCOPE_XMLSERIALIZE_FUNC,
    SQL_SCOPE_SP_ADDTYPE,
    SQL_SCOPE_CONVERT_FUNC,
    SQL_SCOPE_CAST_FUNC,
    SQL_SCOPE_OBJ_TYPE_DECL,

    // SQL SELECT statement scope
    SQL_SEL_INSERT,
    SQL_SEL_CURSOR,
    SQL_SEL_OPEN,
    SQL_SEL_FROM,
    SQL_SEL_EXP,
    SQL_SEL_SET_UNION,
    SQL_SEL_FOR,
    SQL_SEL_IN_PREDICATE,
    SQL_SEL_EXISTS_PREDICATE,
    SQL_SEL_SELECT_LIST,
    SQL_SEL_FOREACH,
    SQL_SEL_UPDATE_SET,
    SQL_SEL_UPDATE_FROM,
    SQL_SEL_JOIN,
    SQL_SEL_CREATE_TEMP_TABLE_AS,
    SQL_SEL_EXPORT,
    SQL_SEL_WITH_CTE,
    SQL_SEL_VIEW,

    // SQL Statement scope
    SQL_STMT_ALTER_TABLE,
    SQL_STMT_SELECT,
    SQL_STMT_INSERT,
    SQL_STMT_UPDATE,
    SQL_STMT_DELETE,

}
