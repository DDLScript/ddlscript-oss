SELECT __S__.ID
        , __S__.TITLE
        , __S__.PROJECT_ID
        , __S__.DESCRIPTION
        , __S__.TIMESTAMP_CREATED
        , __S__.TIMESTAMP_UPDATED
        , __S__.USER_ID_CREATED
        , __S__.USER_ID_UPDATED

FROM `Script` __S__

INNER JOIN (
    SELECT CAST(? AS INT) AS SCRIPT_ID
) AS __INPUT__ ON (
    __INPUT__.SCRIPT_ID = __S__.ID
)