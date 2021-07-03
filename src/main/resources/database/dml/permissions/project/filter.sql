SELECT __PP__.ID, __PP__.NAME
FROM `ProjectPermission` __PP__

CROSS JOIN (
    SELECT CAST(? AS INT) AS PROJECT_ID
            , CAST(? AS INT) AS ACCESSIBLE_TO_USER_ID
) __INPUT__

LEFT JOIN `ProjectUserProjectPermission` __PUSP__ ON (
    __PUSP__.PROJECT_ID = __INPUT__.PROJECT_ID
    AND __PUSP__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND __PUSP__.PROJECTPERMISSION_ID = __PP__.ID
)

LEFT JOIN `ProjectUserGroupProjectPermission` __PUGSP__ ON (
    __PUGSP__.PROJECT_ID = __INPUT__.PROJECT_ID
    AND __PUGSP__.PROJECTPERMISSION_ID = __PP__.ID
)

LEFT JOIN `UserGroupMember` __UGM__ ON (
    __UGM__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND __UGM__.USERGROUP_ID = __PUGSP__.USERGROUP_ID
)

INNER JOIN `User` __U__ ON (
    __U__.ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND (
        __U__.ID = __PUSP__.USER_ID
        OR __U__.ID = __UGM__.USER_ID
    )
)