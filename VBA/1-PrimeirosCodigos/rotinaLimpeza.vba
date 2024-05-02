Option Explicit

'Rotina para limpeza de dados
Sub sbLimpaDados()

    Dim lContador As Long

    'Inicia a variavel de linha
    lContador = 2

    'Cria uma cópia da planilha
    ActiveSheet.Copy After:=Sheets(1)
    
    ActiveSheet.Name = "Revisada-" & format(now(), "HH-mm-ss")

    'Reptetição para cada uma das linhas da planilha
    Do While Trim(Cells(lContador, 1)) <> vbNullString
        'COLUNA A: Ajustando o id do cliente
        If Left(Cells(lContador, 1), 5) <> "byte_" Then
            Cells(lContador, 1) = "byte_" & Cells(lContador, 1)
        End If
        
        'COLUNA B: Limpando caracteres estranhos no nome do cliente
        Cells(lContador, 2) = Replace(Cells(lContador, 2), "#", "")
        Cells(lContador, 2) = Replace(Cells(lContador, 2), "$", "")
        Cells(lContador, 2) = Replace(Cells(lContador, 2), "*", "")
        Cells(lContador, 2) = Replace(Cells(lContador, 2), "%", "")
        Cells(lContador, 2) = Replace(Cells(lContador, 2), "&", "")

        'COLUNA C: Ajustando o valor moeda
        Cells(lContador, 3) = Replace(Cells(lContador, 3), "R$", "")
        Cells(lContador, 3) = Replace(Cells(lContador, 3), ",", "")
        Cells(lContador, 3) = Replace(Cells(lContador, 3), ".", ",")
        Cells(lContador, 3).NumberFormat = _
            "-[$R$-pt-BR] * #,##0.00_-;-[$R$-pt-BR] * #,##0.00_-;_-[$R$-pt-BR] *

        'COLUNA D: Criando o e-mail do cliente
        Cells(lContador, 4) = Cells(lContador, 3) & "@bytebank.com"

        lContador = lContador + 1
    Loop

    'Formata como tabela
    Range("A1").Select
    Range (Selection, Selection.End(xlDown)).Select
    Range (Selection, Selection.End(xlToRight)).Select
    Application.CutCopyMode = False
    ActiveSheet.ListObjects.Add(xlSrcRange, Range("$A$1:$D$10"), , xlYes).
        "Tabela1"
    
End Sub