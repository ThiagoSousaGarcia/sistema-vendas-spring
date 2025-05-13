# 🏗️ Modernização do Fluxo de Efetivação de Contratação
### Projeto de transformação do processo de contratação no CG Investimentos

---

## ✅ Motivação e contexto da mudança

O processo anterior de efetivação de contratação de produtos no CG Investimentos estava baseado em uma estrutura centralizada, composta por duas aplicações Java:

- **Orquestrador**: responsável por coordenar o fluxo completo, como uma máquina de estados.
- **ACL Integrações**: encarregado de executar as chamadas externas ou internas correspondentes a cada etapa do processo.

Essa arquitetura foi eficiente durante um período, mas com o aumento da complexidade dos produtos e das integrações, surgiram diversos **desafios recorrentes**:

### 🔴 Problemas identificados:

- **Baixa rastreabilidade**: era difícil identificar em tempo real qual o estado da contratação e em que etapa ela estava.
- **Manutenção complexa**: alterações em uma etapa exigiam mudanças em vários pontos do código, muitas vezes exigindo testes regressivos complexos.
- **Acoplamento excessivo**: as responsabilidades estavam entrelaçadas entre orquestração e execução, dificultando a modularidade e reuso.
- **Logs dispersos e difíceis de interpretar**: para entender uma falha, era necessário reunir logs de múltiplas fontes.
- **Resiliência limitada**: em caso de erro, o rollback nem sempre era claro ou facilmente executável.
- **Escalabilidade manual**: a arquitetura não se beneficiava de mecanismos nativos de escalabilidade ou paralelismo.

---

## 🧠 Solução adotada: AWS Step Functions + AWS Lambda

Para resolver esses pontos e modernizar o fluxo de contratação, migramos o processo para uma **arquitetura serverless**, utilizando os serviços **AWS Step Functions** e **AWS Lambda**, com apoio de **SNS** para notificações e incidentes automatizados.

---

### 🔄 Como funciona o novo fluxo

- Cada etapa da contratação foi transformada em uma **função Lambda desacoplada** com responsabilidade única.
- A **orquestração do fluxo** é feita por um **Step Function**, que executa os passos sequencial ou paralelamente, conforme definido.
- Em caso de sucesso, o processo avança naturalmente.  
- Em caso de falha, o Step Function aciona automaticamente a **etapa de compensação (rollback)** correspondente e envia uma **notificação via SNS**.
- O SNS dispara alertas e também pode abrir **incidentes automatizados** em sistemas de monitoração como CloudWatch, Datadog, PagerDuty, etc.

---

## 🎯 Benefícios detalhados da nova abordagem

### 📍 1. Rastreabilidade em tempo real

- Através da interface gráfica do AWS Step Functions, é possível acompanhar em tempo real cada etapa da contratação.
- Visualização clara do fluxo, com status de **sucesso, erro ou rollback**, permitindo ações rápidas pelo time de produto ou suporte.

### 🔗 2. Separação clara de responsabilidades

- Cada função Lambda tem um propósito específico, como "Validar Dados", "Criar Conta", "Registrar Contrato", etc.
- Isso facilita **testes unitários**, **auditoria** e **alterações pontuais** sem afetar o fluxo inteiro.

### 🧩 3. Arquitetura desacoplada e escalável

- As Lambdas podem ser desenvolvidas, atualizadas ou substituídas **independentemente**.
- Escalabilidade automática: a AWS escala cada função sob demanda, sem necessidade de infraestrutura dedicada.

### 🧯 4. Gestão proativa de erros e incidentes

- Falhas geram eventos automaticamente:
  - **Rollback** automático da contratação parcial.
  - **Notificações automáticas** via SNS.
  - **Criação de incidentes** para o time responsável.
- A resolução se torna **mais rápida** e com **menos dependência da área técnica** para identificar o problema.

### 🔁 5. Reaproveitamento e padronização

- As Lambdas podem ser reutilizadas em outros fluxos de negócio (ex: cancelamento, renovação, etc.).
- Padronização das chamadas externas (ex: chamadas a APIs de terceiros) e tratamento de falhas.

### 🛠️ 6. Agilidade para evolução do produto

- Novas etapas no processo podem ser adicionadas ao Step Function com **impacto mínimo** nas funções existentes.
- Facilita a **experiência de experimentação e testes A/B**, sem refatorações complexas.

---

## 🧱 Comparativo antes e depois

| Aspecto                     | Arquitetura Antiga (Java)             | Arquitetura Nova (Step Functions + Lambda) |
|----------------------------|----------------------------------------|--------------------------------------------|
| Orquestração               | Centralizada na aplicação Orquestrador| Distribuída via Step Function              |
| Execução das etapas        | ACL Integrações (Java)                | Lambdas independentes                      |
| Rastreabilidade            | Logs manuais e complexos              | Visível graficamente e em tempo real       |
| Tratamento de falhas       | Parcial, com rollback manual          | Rollback automático + notificações SNS     |
| Notificações e alertas     | Manual ou inexistente                 | Automáticas e com incidentes integrados    |
| Escalabilidade             | Limitada                              | Automática, nativa da AWS                  |
| Tempo de manutenção        | Alto (rebuild e deploy completo)      | Baixo (alterações isoladas por função)     |
| Testes                     | Difíceis e integrados                 | Unitários por função                       |
| Tempo de resolução de erro | Elevado                               | Reduzido com rastreabilidade nativa        |

---

## 📁 Alterações realizadas no projeto

> *(Liste aqui todas as alterações realizadas. Por exemplo:)*

- Criação de Step Function com definição de estados.
- Desenvolvimento de funções Lambda individuais para cada etapa.
- Integração com SNS para envio de falhas e alertas.
- Refatoração do código Java legado para adaptação.
- Criação de políticas IAM e logs estruturados.
- Configuração de mecanismos de rollback por falha.
- Criação de dashboards e monitoração.

---

## 📊 Visão do novo fluxo

> *(Inserir aqui os prints do Step Function com execução normal, falha, e notificação do SNS. Pode usar imagens como:)*

- `print-fluxo-completo.png`
- `print-erro-e-rollback.png`
- `print-alerta-sns.png`

---

## 📎 Conclusão

A migração do fluxo de efetivação para a arquitetura baseada em **AWS Step Functions e Lambdas** representa um avanço significativo na **modernização, rastreabilidade, confiabilidade e capacidade de evolução** do CG Investimentos.

Essa mudança traz não apenas benefícios técnicos, mas também **impactos positivos diretos para o time de produto**:

- Redução do tempo para identificar e resolver erros.
- Maior visibilidade sobre o que está acontecendo nas contratações.
- Mais agilidade para evoluir o processo conforme mudanças de negócio.

---

