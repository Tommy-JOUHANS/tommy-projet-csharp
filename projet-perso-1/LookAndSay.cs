using System;
using System.Text;
using System.Diagnostics;

class LookAndSay
{
    // Version LENTE : string immuable → génère des milliers d'objets garbage
    static string NextStepSlow(string s)
    {
        string result = "";
        int i = 0;
        while (i < s.Length)
        {
            char c = s[i];
            int count = 0;
            while (i < s.Length && s[i] == c) { count++; i++; }
            result += count.ToString() + c; // ← nouvelle string à chaque fois !
        }
        return result;
    }

    // Version RAPIDE : StringBuilder → buffer mutable, zéro garbage intermédiaire
    static string NextStepFast(string s)
    {
        var sb = new StringBuilder();
        int i = 0;
        while (i < s.Length)
        {
            char c = s[i];
            int count = 0;
            while (i < s.Length && s[i] == c) { count++; i++; }
            sb.Append(count);
            sb.Append(c);
        }
        return sb.ToString();
    }

    static void Run(string label, Func<string, string> step, int depth)
    {
        var sw = Stopwatch.StartNew();
        string seq = "1";
        for (int i = 0; i < depth; i++)
            seq = step(seq);
        sw.Stop();
        Console.WriteLine($"[{label}] Profondeur {depth} | Longueur finale : {seq.Length} | Temps : {sw.ElapsedMilliseconds} ms");
    }

    static void Main()
    {
        const int DEPTH = 40;
        Console.WriteLine("=== Look and Say – comparaison string vs StringBuilder ===\n");
        Run("StringBuilder (rapide)", NextStepFast, DEPTH);
        Run("string       (lente) ", NextStepSlow, DEPTH);
    }
}
