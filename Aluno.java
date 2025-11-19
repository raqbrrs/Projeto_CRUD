//Obter o ano atual do sistema
        int anoAtual = java.time.Year.now().getValue();
        
        // Exibir o ano atual no lblAno
        lblAno.setText(Integer.toString(anoAtual));
        
        // Calcular a idade
        int an = Integer.parseInt(txtAN.getValue().toString());
        int id = anoAtual - an;
        
        // Exibir a idade no lblIdade
        lblIdade.setText(Integer.toString(id)); 
