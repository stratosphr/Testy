MACHINE name

    CONST_DEFS
        bool c0 = 0 + 4
        bool c1 = 2 + 1.0 + 2
        bool c2 = true || false && 0 && c0 && c1 || 17 + c0 && 4.0 > 0
        int c3 = 0 + 1.0 + 4
        int c4 = 1 + c3 + c4
        int c4 = 2
        real c5 = 42 + ---c4 + -5 + 4 || true && c5
        set<real> c6 = {x, 42.0, {}, true}
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
        real c15 = 3

    SET_DEFS
        set<bool> s0 = {}
        set<set<bool>> s1 = {s0, c0, {c1}, c3}
        set<set<set<bool>>> s2 = {{}, s0, s1, c7, c8, c9}
        set<set<set<bool>>> s3 = s2
        set<set<set<bool>>> s3 = x
        set<bool> s4 = {10}
        set<bool> s5 = 4
        set<string> bat = {"ok", "ko"}

    VAR_DEFS
        set<bool> v0 : s0
        set<set<bool>> v1 : s1
        set<set<set<set<bool>>>> v2 : {s1, s2, s3}
        set<set<set<set<bool>>>> v3 : {s1, s2, s3, {{{c0}}}, {{{c1}}}}
        set<real> v4 : {c3 + 1, c4 + 2, c5 + 3}
        set<real> v5 : {}
        set<int> v6 : {}
        real v7 : v5
        real v7 : 10
        real v8 : {v5}
        int v9 : v0
        int v10 : v6
        int v11 : v5

    FUN_DEFS
        int -> bool f0 : {} -> {}
        real -> int f1 : {c3, c4, c5} -> {}
        bool -> real f2 : {} -> {c3, c4, c5}
        real -> real f3 : {c3, c4, c5} -> {c3, c4, c5}
        bool -> set<bool> f4 : s0 -> s1
        set<set<bool>> -> set<bool> f5 : s2 -> c7
        set<set<real>> -> set<set<bool>> f6 : c8 -> s2
        int -> bool f7 : {3, 4.0} -> {4}
        int -> bool f7 : {3, 4.0} -> {false}
        int -> bool f8 : {3, c5} -> {false}
        bool -> real f8 : {3, 5 + c4} -> {false}
        bool -> real f9 : v0 -> v6
        set<real> -> set<real> f10 : c12 -> {c12}
        set<real> -> set<real> f11 : c12 -> c12
        set<real> -> set<real> f12 : {c12} -> {c12, c12}

    INVARIANT
        a + 50

    INITIALISATION
        SELECT
            17.0 + (v11 = false)
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
                        v1 != v3 > 50
                    THEN
                        v4 := c4 + 2;
                        v1 := 0;
                        v2 := c0 => c1
                    END
                OR
                    ANY
                        int x : c11,
                        bool y : {true, false},
                        int -> int f : {c3, c4} -> {1 + 3 + 4 - 5 + 6, f3(-4)}
                    WHERE
                        f3(x) = x <=> 10 < a : 10 <=> 50 > 10
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

        v9 =
            ANY
                set<int> x : {c11}
                set<bool> y : {true, false}
                int -> int f : {v0, v1} -> {v0, v1, v2, 1 + 3 + 4 - 5 + 6}
                set<int> f : {10, 15}
                int -> int g : {v0, v1} -> {v0, v1, v2, 1 + 3 + 4 - 5 + 6}
                set<int> h : {10, 15}
            WHERE
                f3(x) = f(2) &&
                f(false) = true &&
                f(10) = false &&
                f(10) = 42.0 &&
                f3(x) = x <=> 10 <= a : 42 <=> 50 >= 10 / 4.0 % 5 &&
                k(f3) = false => x : {c11}
            THEN
                SELECT
                    {v1} != v3
                THEN
                    v1 := v3;
                    f(x) := x;
                    c0 := false;
                    x := 0;
                END
            END

        e1 =
            SKIP

        e2 =
            SELECT
                (c0 || €(set<real> x : {v4, c5}, set<bool> y : {true, false}).(x = v4)) &&
                (c1 || #(int x : {10, 40}, set<real> x : {v4, 10 + 11, 9 - c5}, set<real> y : {x, 0}).((x != v4))) &&
                €(int a : {0}, int b : {0, 1}).(#(int a : {0}, int -> int f : {1, 2} -> {false, 2}, real -> real f : {a % b = 0} -> {42.0, 10.4}).(a != b && a != f))
            THEN
                v4 := c4 + 2;
                v4 := c4 + 2;
                f3(c4) := c5;
                v1 := 0;
                v2 := c0 => c1;
                c0 := 10;
                bat := "ok";
                bat(4) := "t10";
                f(2) := 4;
            END
