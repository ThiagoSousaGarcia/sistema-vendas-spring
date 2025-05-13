# 🔄 Modernização do Fluxo de Contratação e Manutenção – CG Investimentos

---

## 1. 🎯 Motivação da Mudança

O processo antigo utilizava duas aplicações Java: uma chamada **Orquestrador**, que executava uma máquina de estados, e outra chamada **ACL Integrações**, responsável pelas chamadas externas.

Apesar de funcional, essa arquitetura apresentava limitações claras:

- Rastreabilidade baixa (logs difíceis de entender e dispersos).
- Alto acoplamento entre orquestração e execução.
- Dificuldade de manutenção e evolução do fluxo.
- Gestão manual de erros e ausência de notificações automáticas.
- Baixa visibilidade para os times de produto e suporte.

Esses problemas afetavam não apenas o **fluxo de efetivação**, mas também o de **manutenção dos contratos** (como alterações e cancelamentos).

---

## 2. 🧠 Solução Adotada

Para tornar o processo mais moderno, escalável e observável, migramos para uma arquitetura **serverless** usando:

- **AWS Step Functions**: orquestração visual e auditável do fluxo.
- **AWS Lambda**: funções desacopladas, cada uma responsável por uma etapa.
- **SNS (Simple Notification Service)**: envio automático de alertas e incidentes.
- **Rollback automático**: etapas de desfazimento em caso de falhas.

Essa abordagem foi aplicada tanto na **efetivação** quanto na **manutenção dos contratos**, permitindo controle total de ponta a ponta.

---

## 3. 🚀 Benefícios da Nova Arquitetura

### ✅ Rastreabilidade e Visibilidade
- Visualização em tempo real do fluxo via Step Functions.
- Fácil identificação do ponto de falha e tempo gasto por etapa.

### ✅ Separação de Responsabilidades
- Cada função é independente e com responsabilidade única.
- Permite testes e mudanças sem impactar o fluxo completo.

### ✅ Resiliência e Escalabilidade
- Falhas disparam notificações automáticas e realizam rollback.
- Execução paralela e escalável conforme a demanda.

### ✅ Agilidade de Evolução
- Adição ou alteração de etapas de forma rápida e segura.
- Redução do tempo de desenvolvimento e validação.

---

## 4. 🛠️ Alterações Realizadas + Visão do Fluxo

> *(Espaço para incluir detalhes técnicos e prints)*

- Definição do fluxo completo em AWS Step Functions.
- Criação das funções Lambda por etapa (efetivação e manutenção).
- Integração com SNS para alertas automáticos.
- Configuração de rollback por erro.
- Inserção de logs estruturados e dashboards de monitoração.

> **Exemplos a incluir:**
> - print-step-function-fluxo.png  
> - print-erro-sns.png  
> - print-rollback.png

---

### ✅ Conclusão

Com a nova arquitetura, os fluxos de **efetivação e manutenção** passaram a ser:

- Mais confiáveis
- Mais fáceis de monitorar
- Mais simples de evoluir

Essa mudança reduz drasticamente o esforço para resolver problemas, aumenta a transparência para os times de produto e suporte, e prepara o CG Investimentos para uma jornada de inovação mais ágil e segura.

---
