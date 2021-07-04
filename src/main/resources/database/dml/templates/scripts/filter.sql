SELECT DISTINCT __ST__.TIMESTAMP_CREATED
          , __ST__.TIMESTAMP_UPDATED
          , __ST__.PROJECT_ID
          , __ST__.SCRIPT_ID
          , __ST__.APPLIED_TO_EACH_STATEMENT
          , __ST__.APPLIED_AFTER
          , __ST__.QUERY
          , __ST__.USER_ID_CREATED
          , __ST__.USER_ID_UPDATED
FROM `ScriptTemplate` __ST__

CROSS JOIN (
    SELECT CAST(? AS INT) AS PROJECT_ID
            , CAST(? AS INT) AS SCRIPT_ID
) AS __INPUT__

WHERE __ST__.PROJECT_ID = __INPUT__.PROJECT_ID
  AND __ST__.SCRIPT_ID = __INPUT__.SCRIPT_ID
