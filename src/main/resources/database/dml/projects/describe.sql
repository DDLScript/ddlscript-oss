SELECT DISTINCT __P__.TIMESTAMP_CREATED
          , __P__.TIMESTAMP_DELETED
          , __P__.ID
          , __P__.NAME
          , __P__.DATABASE_TYPE
          , __P__.USER_ID_CREATED
          , __P__.TEMPLATE_BEFORE_ALL
          , __P__.TEMPLATE_BEFORE_EACH
          , __P__.TEMPLATE_AFTER_EACH
          , __P__.TEMPLATE_AFTER_ALL
FROM `Project` __P__

INNER JOIN (
    SELECT CAST(? AS INT) AS PROJECT_ID
            ,CAST(? AS INT) AS ACCESSIBLE_TO_USER_ID
) AS __INPUT__ ON (
    __INPUT__.PROJECT_ID = __P__.ID
)

LEFT JOIN `ProjectUser` __PU__ ON (
    __PU__.PROJECT_ID = __P__.ID
    AND __PU__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
)

LEFT JOIN `ProjectUserGroup` __PUG__ ON (
    __PUG__.PROJECT_ID = __P__.ID
)

LEFT JOIN `UserGroupMember` __UGM__ ON (
    __UGM__.USERGROUP_ID = __PUG__.USERGROUP_ID
    AND __UGM__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
)

INNER JOIN `User` __U__ ON (
    __U__.ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND (
        __U__.ID = __PU__.USER_ID
        OR __U__.ID = __UGM__.USER_ID
        -- OR USER CAN SEE ALL PROJECTS
    )
)

WHERE __P__.TIMESTAMP_DELETED IS NULL