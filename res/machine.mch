MACHINE name

    CONST_DEFS
        bool c0 = false
        bool c1 = true
        bool c2 = true || false && c0 && c1 || c0
        int c3 = 0
        int c4 = 1 + c3
        real c5 = 42.0 + ---c4
        set<real> c6 = {}
        set<set<bool>> c7 = {{}, {c0, c1, c2}, {c3 > 0, c6 = {}}}
        set<set<set<real>>> c8 = {
            {
                {c3, c4, c5},
                {c5, c4, c5},
                {c4, c5},
                {c5}
            },
            {
                {},
                {c4 + 0},
                c6,
                {},
                {},
                {c5, 64.0}
            },
            {}
        }
        set<set<set<real>>> c9 = c8
        bool c10 = c0 : {false, !c1, c3 !: {0, 1, 2, 3}}
        set<int> c11 = [c3 .. 1 + c4 * 2 * 4 + 3 + 4]
        set<real> c12 = [c3..c4]
        bool c13 = {4, 5, 1.4} = {2.3, 2}
        set<set<set<set<real>>>> c14 = {c9, c8, {{{1.0, 4}}}, c9}

    SET_DEFS
        set<bool> s0 = {}
        set<set<bool>> s1 = {s0, {c0}, {c1}, {c3}}
        set<set<set<bool>>> s2 = {s0, s1, c7, c8, c9}
        set<set<set<bool>>> s3 = s2

    VAR_DEFS
        set<bool> v0 : s0
        set<set<bool>> v1 : s1
        set<set<set<set<bool>>>> v2 : {s1, s2, s3}
        set<set<set<set<bool>>>> v3 : {s1, s2, s3, {{{c0}}}, {{{c1}}}}
        set<real> v4 : {c3 + 1, c4 + 2, c5 + 3}

    FUN_DEFS
        int -> bool f0 : {} -> {}
        real -> int f1 : {c3, c4, c5} -> {}
        bool -> real f2 : {} -> {c3, c4, c5}
        real -> real f3 : {c3, c4, c5} -> {c3, c4, c5}
        bool -> set<bool> f4 : s0 -> s1
        set<set<bool>> -> set<bool> f5 : s2 -> c7
        set<set<real>> -> set<set<bool>> f6 : c8 -> s2

    INVARIANT
        !(v2 != v3)

    INITIALISATION
        SELECT
            v1 = v3 || v4 = c3
        THEN
            IF
                v2 != {} && v4 : {c3 + 1, c4 + 2}
            THEN
                SELECT
                    v1 = v3 || v4 != c4
                THEN
                    v4 := c4 + 2;
                    v1 := 0;
                    v2 := c0 => c1
                END
            ELSE
                CHOICE
                    v4 := c5 + 3
                OR
                    v4 := c4 + 2;
                    v1 := 0;
                OR
                    SKIP
                OR
                    SELECT
                        v1 != v3
                    THEN
                        v4 := c4 + 2;
                        v1 := 0;
                        v2 := c0 => c1
                    END
                OR
                    ANY
                        int x : c11,
                        bool y : {true, false},
                        int -> real f : {0, 1} -> {v4, 1 + 3 + 4 - 5 + 6}
                    WHERE
                        f3(x) = x
                    THEN
                        SELECT
                            v1 != v3
                        THEN
                            v1 := v3
                        END
                    END
                END
            END
        END

    EVENTS

        e0 =
            ANY
                int x : c11,
                bool y : {true, false},
                int -> real f : {0, 1} -> {v4, 1 + 3 + 4 - 5 + 6}
            WHERE
                f3(x) = x
            THEN
                SELECT
                    v1 != v3
                THEN
                    v1 := v3
                END
            END

        e1 =
            SKIP

        e2 =
            SELECT
                (c0 || €(int x : {0, 2, 4}, set<bool> y : c7).(false : y || x = 4)) &&
                (c1 || #(real x : {v4, 10 + 11, 9 - c5}, real y : {x, 0}).((x != v4)))
            THEN
                v4 := c4 + 2;
                f3(c4) := 5;
                v1 := 0;
                v2 := c0 => c1
            END
