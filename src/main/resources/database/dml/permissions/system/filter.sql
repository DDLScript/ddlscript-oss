SELECT __SP__.ID, __SP__.NAME
FROM `SystemPermission` __SP__

CROSS JOIN (
    SELECT CAST(? AS INT) AS ACCESSIBLE_TO_USER_ID
) __INPUT__

LEFT JOIN `UserSystemPermission` __USP__ ON (
    __USP__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND __USP__.SYSTEMPERMISSION_ID = __SP__.ID
)

LEFT JOIN `UserGroupSystemPermission` __UGSP__ ON (
    __UGSP__.SYSTEMPERMISSION_ID = __SP__.ID
)

LEFT JOIN `UserGroupMember` __UGM__ ON (
    __UGM__.USER_ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND __UGM__.USERGROUP_ID = __UGSP__.USERGROUP_ID
)

INNER JOIN `User` __U__ ON (
    __U__.ID = __INPUT__.ACCESSIBLE_TO_USER_ID
    AND (
        __U__.ID = __USP__.USER_ID
        OR __U__.ID = __UGM__.USER_ID
    )
)