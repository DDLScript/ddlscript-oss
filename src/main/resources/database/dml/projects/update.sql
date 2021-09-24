UPDATE `Project` __P__
SET NAME = IFNULL(CAST(? AS VARCHAR(100)), __P__.NAME)
    , TEMPLATE_BEFORE_ALL = IFNULL(CAST(? AS TEXT), __P__.TEMPLATE_BEFORE_ALL)
    , TEMPLATE_BEFORE_EACH = IFNULL(CAST(? AS TEXT), __P__.TEMPLATE_BEFORE_EACH)
    , TEMPLATE_AFTER_EACH = IFNULL(CAST(? AS TEXT), __P__.TEMPLATE_AFTER_EACH)
    , TEMPLATE_AFTER_ALL = IFNULL(CAST(? AS TEXT), __P__.TEMPLATE_AFTER_ALL)
WHERE __P__.ID = ?