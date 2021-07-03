INSERT INTO `ProjectUserProjectPermission` (TIMESTAMP_CREATED, PROJECT_ID, USER_ID, PROJECTPERMISSION_ID, USER_ID_CREATED)
SELECT
    CURRENT_TIMESTAMP()
    , __INPUT__.PROJECT_ID
    , __INPUT__.USER_ID
    , __PP__.ID
    , __INPUT__.USER_ID_CREATED

FROM `ProjectPermission` __PP__

CROSS JOIN (
    SELECT CAST(? AS INT) AS PROJECT_ID
            , CAST(? AS INT) AS USER_ID
            , CAST(? AS INT) AS USER_ID_CREATED
) AS __INPUT__